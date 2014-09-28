package com.eventstech.service.impl;

import com.eventstech.db.entity.SpeakerCategory;
import com.eventstech.db.repositories.SpeakerCategoryRepository;
import com.eventstech.rest.dto.SpeakerCategoryDto;
import com.eventstech.service.SpeakerCategoryService;
import com.eventstech.test.db.AbstractDbTest;
import org.junit.Assert;
import org.junit.Ignore;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.test.context.ContextConfiguration;

import java.util.Arrays;

import static org.hamcrest.CoreMatchers.nullValue;
import static org.hamcrest.core.IsEqual.equalTo;
import static org.hamcrest.core.IsNull.notNullValue;

/**
 * Created by vanish on 8/7/14.
 */
@ContextConfiguration(locations = {"classpath*:spring/spring-events-services-test-context.xml"})
@Ignore
public class SpeakerCategoryServiceImplTest extends AbstractDbTest {

    @Autowired
    private SpeakerCategoryService speakerCategoryService;
    @Autowired
    private SpeakerCategoryRepository speakerCategoryRepository;

    @Test
    public void saveTest() {
        SpeakerCategoryDto speakerCategory = new SpeakerCategoryDto();
        speakerCategory.setName("event");
        speakerCategory.setSlug("event");
        speakerCategory = speakerCategoryService.save(speakerCategory);

        Assert.assertThat(speakerCategoryRepository.findOne(speakerCategory.getId()), notNullValue());
    }

    @Test
    public void findAllTest() {
        SpeakerCategory speakerCategoryOne = new SpeakerCategory();
        speakerCategoryOne.setName("event");
        speakerCategoryOne.setSlug("event");
        SpeakerCategory speakerCategoryTwo = new SpeakerCategory();
        speakerCategoryTwo.setName("event");
        speakerCategoryTwo.setSlug("event1");
        speakerCategoryRepository.save(Arrays.asList(speakerCategoryOne, speakerCategoryTwo));

        Assert.assertThat(speakerCategoryService.findAll().size(), equalTo(2));
    }

    @Test
    public void deleteTest() {
        SpeakerCategory speakerCategory = new SpeakerCategory();
        speakerCategory.setName("event");
        speakerCategory.setSlug("event");
        speakerCategory = speakerCategoryRepository.save(speakerCategory);

        speakerCategoryService.delete(speakerCategory.getId());

        Assert.assertThat(speakerCategoryRepository.findOne(speakerCategory.getId()), nullValue());
    }


    @Test(expected = DataIntegrityViolationException.class)
    public void slugShouldBeUniqueTest() {
        SpeakerCategoryDto speakerCategoryOne = new SpeakerCategoryDto();
        speakerCategoryOne.setName("event");
        speakerCategoryOne.setSlug("event");
        SpeakerCategoryDto speakerCategoryTwo = new SpeakerCategoryDto();
        speakerCategoryTwo.setName("event");
        speakerCategoryTwo.setSlug("event");
        speakerCategoryService.save(speakerCategoryOne);
        speakerCategoryService.save(speakerCategoryTwo);
    }
}