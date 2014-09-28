package com.eventstech.aspect;

import com.eventstech.annotation.DeleteIndex;
import com.eventstech.annotation.Indexed;
import com.eventstech.annotation.IndexedEntityType;
import org.apache.commons.lang3.StringUtils;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.reflect.MethodSignature;
import org.dozer.DozerBeanMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.core.ParameterNameDiscoverer;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;
import org.springframework.util.ReflectionUtils;

import java.io.Serializable;
import java.lang.reflect.Field;
import java.lang.reflect.Method;

/**
 * Created by vanish on 8/23/14.
 */
@Aspect
public class IndexAspect implements ApplicationContextAware {

    private static final Logger LOG = LoggerFactory.getLogger(IndexAspect.class);

    private ApplicationContext appContext;
    private DozerBeanMapper dozer;
    private ParameterNameDiscoverer parameterNameDiscoverer;

    @After("@annotation(com.eventstech.annotation.DeleteIndex)")
    public void deleteIndex(JoinPoint joinPoint) throws IllegalAccessException {
        MethodSignature methodSignature = (MethodSignature) joinPoint.getSignature();
        Method method = methodSignature.getMethod();
        DeleteIndex deleteIndex = method.getAnnotation(DeleteIndex.class);
        Serializable id = getId(joinPoint, deleteIndex, method);

        ElasticsearchRepository repository = appContext.getBean(deleteIndex.repository());
        if (repository.exists(id)) {
            repository.delete(id);
        }
    }

    private Serializable getId(JoinPoint joinPoint, DeleteIndex deleteIndex, Method method) throws IllegalAccessException {
        Object obj = getSourceEntity(joinPoint.getArgs(), method, deleteIndex.indexParameterName());
        if (StringUtils.isEmpty(deleteIndex.indexParameterIdField())) {
            return (Serializable) obj;
        } else {
            Field field = ReflectionUtils.findField(obj.getClass(), deleteIndex.indexParameterIdField());
            return (Serializable) field.get(obj);
        }
    }

    @AfterReturning(pointcut = "@annotation(com.eventstech.annotation.Indexed)", returning = "result")
    public void index(JoinPoint joinPoint, Object result) {
        MethodSignature methodSignature = (MethodSignature) joinPoint.getSignature();
        Method method = methodSignature.getMethod();
        Indexed indexed = method.getAnnotation(Indexed.class);

        Object sourceEntity;
        if (indexed.type() == IndexedEntityType.METHOD_PARAM) {
            sourceEntity = getSourceEntity(joinPoint.getArgs(), method, indexed.indexParameterName());
        } else {
            sourceEntity = result;
        }
        Object entity = dozer.map(sourceEntity, indexed.documentClass());

        ElasticsearchRepository repository = appContext.getBean(indexed.repository());
        repository.index(entity);
    }

    private Object getSourceEntity(Object[] args, Method method, String fieldName) {
        String[] parametersNames = parameterNameDiscoverer.getParameterNames(method);
        for (int i = 0; i < parametersNames.length; i++) {
            if (parametersNames[i].equals(fieldName)) {
                return args[i];
            }
        }
        LOG.error("Field with name {} is not defined for method {}", fieldName, method.getName());
        throw new IllegalArgumentException(String.format("Field with name %s is not defined for method %s", fieldName, method.getName()));
    }

    @Override
    public void setApplicationContext(ApplicationContext appContext) throws BeansException {
        this.appContext = appContext;
    }

    public void setDozer(DozerBeanMapper dozer) {
        this.dozer = dozer;
    }

    public void setParameterNameDiscoverer(ParameterNameDiscoverer parameterNameDiscoverer) {
        this.parameterNameDiscoverer = parameterNameDiscoverer;
    }
}
