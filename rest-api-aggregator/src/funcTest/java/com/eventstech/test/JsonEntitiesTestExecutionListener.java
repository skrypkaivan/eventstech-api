package com.eventstech.test;

import com.eventstech.db.entity.AbstractEntity;
import com.google.common.collect.Lists;
import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonParser;
import com.google.gson.stream.JsonReader;
import lombok.extern.slf4j.Slf4j;
import org.dozer.Mapper;
import org.springframework.context.ApplicationContext;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.test.context.TestContext;
import org.springframework.test.context.support.AbstractTestExecutionListener;

import javax.sql.DataSource;
import java.io.FileReader;
import java.io.Serializable;
import java.lang.reflect.Method;
import java.net.URL;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;

/**
 * Author: Ivan Skrypka
 * Copyright © 2014 Eventstech.com.ua.
 */
@SuppressWarnings("unchecked")
@Slf4j
public class JsonEntitiesTestExecutionListener extends AbstractTestExecutionListener {

    public static final String ENTITIES_TO_DELETE_BEAN_NAME = "entitiesToDeleteBeforeEachTest";
    public static final String DOCUMENTS_TO_DELETE_BEAN_NAME = "documentsToDeleteBeforeEachTest";
    public static final String ENTITY_TO_DOCUMENT_BEAN_NAME = "entityToDocumentConversionMap";

    private JsonParser parser;
    private Gson gson;
    private Mapper dozer;
    private DataSource dataSource;
    private Map<Class<?>, ? extends JpaRepository<AbstractEntity, Serializable>> jpaRepositories;
    private Map<Class<?>, ? extends ElasticsearchRepository> elasticsearchRepositories;
    private Map<Class<?>, Class<?>> entity2DocumentConversion;

    @Override
    public void prepareTestInstance(TestContext testContext) throws Exception {
        ApplicationContext applicationContext = testContext.getApplicationContext();

        this.gson = applicationContext.getBean(Gson.class);
        this.parser = applicationContext.getBean(JsonParser.class);
        this.dozer = applicationContext.getBean(Mapper.class);
        this.dataSource = applicationContext.getBean(DataSource.class);

        this.jpaRepositories = applicationContext.getBean(ENTITIES_TO_DELETE_BEAN_NAME, Map.class);
        this.elasticsearchRepositories = applicationContext.getBean(DOCUMENTS_TO_DELETE_BEAN_NAME, Map.class);
        this.entity2DocumentConversion = applicationContext.getBean(ENTITY_TO_DOCUMENT_BEAN_NAME, Map.class);
    }

    @Override
    public void afterTestMethod(TestContext testContext) throws Exception {
        clearAllTables();
        clearAllIndices();
    }

    @Override
    public void beforeTestMethod(TestContext testContext) throws Exception {
        log.info("Clearing database tables");
        clearAllTables();

        log.info("Database tables cleared. Clearing search indices");
        clearAllIndices();

        log.info("Tables and indices cleared. Populating db and search index with data");
        processJsonEntities(testContext.getTestMethod());
    }

    private void processJsonEntities(Method testMethod) throws Exception {
        if (testMethod.isAnnotationPresent(JsonEntities.class)) {
            JsonEntities jsonEntities = testMethod.getAnnotation(JsonEntities.class);
            for (JsonEntity jsonEntity : jsonEntities.jsonEntities()) {
                log.info("Processing json entity {}, from file {}", jsonEntity.getClass().getName(), jsonEntity.fileLocation());
                processJsonEntity(jsonEntity);
            }
        }
    }

    private void processJsonEntity(JsonEntity jsonEntity) throws Exception {
        try (JsonReader jsonReader = new JsonReader(new FileReader(getImportFilePath(jsonEntity.fileLocation())))) {
            JsonElement jsonElementsArray = parser.parse(jsonReader);
            List<AbstractEntity> entities = Lists.newLinkedList();
            JsonArray asJsonArray = jsonElementsArray.getAsJsonArray();
            if (asJsonArray.size() > 0) {
                String tableName = null;
                for (JsonElement jsonElement : asJsonArray) {
                    AbstractEntity entity = gson.fromJson(jsonElement, jsonEntity.entityClass());
                    tableName = entity.getTableName();
                    entities.add(entity);
                }
                resetId(tableName);
                entities = jpaRepositories.get(jsonEntity.entityClass()).save(entities);
                if (jsonEntity.indexed()) {
                    for (AbstractEntity entity: entities) {
                        Object indexDocument = dozer.map(entity, entity2DocumentConversion.get(jsonEntity.entityClass()));
                        elasticsearchRepositories.get(jsonEntity.entityClass()).index(indexDocument);
                    }
                }
            }
        }
    }

    private void resetId(String tableName) throws SQLException {
        dataSource.getConnection().createStatement().execute(String.format("ALTER TABLE %s ALTER COLUMN id RESTART WITH 1", tableName));
    }

    private void clearAllIndices() {
        for (ElasticsearchRepository elasticsearchRepository: elasticsearchRepositories.values()) {
            elasticsearchRepository.deleteAll();
        }
    }

    private void clearAllTables() {
        for (JpaRepository jpaRepository: jpaRepositories.values()) {
            jpaRepository.deleteAllInBatch();
        }
    }

    private String getImportFilePath(String importFile) {
        URL resource = getClass().getClassLoader().getResource(importFile);
        if (resource == null) {
            throw new IllegalArgumentException(String.format("Input file %s doesn't exist", importFile));
        }
        return resource.getPath();
    }
}
