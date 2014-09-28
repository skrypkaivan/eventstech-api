package com.eventstech.service.impl;

import com.eventstech.db.entity.EventCategory;
import com.eventstech.db.repositories.EventCategoryRepository;
import com.eventstech.rest.dto.EventCategoryDto;
import com.eventstech.service.EventCategoryService;
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
@ContextConfiguration(locations = {"classpath*:/spring/spring-events-services-test-context.xml"})
@Ignore
public class EventCategoryServiceImplTest extends AbstractDbTest {

    @Autowired
    private EventCategoryService eventCategoryService;
    @Autowired
    private EventCategoryRepository eventCategoryRepository;

    @Test
    public void saveTest() {
        EventCategoryDto eventCategory = new EventCategoryDto();
        eventCategory.setName("event");
        eventCategory.setSlug("event");
        eventCategory = eventCategoryService.save(eventCategory);

        Assert.assertThat(eventCategoryRepository.findOne(eventCategory.getId()), notNullValue());
    }

    @Test
    public void findAllTest() {
        EventCategory eventCategoryOne = new EventCategory();
        eventCategoryOne.setName("event");
        eventCategoryOne.setSlug("event");
        EventCategory eventCategoryTwo = new EventCategory();
        eventCategoryTwo.setName("event");
        eventCategoryTwo.setSlug("event1");
        eventCategoryRepository.save(Arrays.asList(eventCategoryOne, eventCategoryTwo));

        Assert.assertThat(eventCategoryService.findAll().size(), equalTo(2));
    }

    @Test
    public void deleteTest() {
        EventCategory eventCategoryOne = new EventCategory();
        eventCategoryOne.setName("event");
        eventCategoryOne.setSlug("event");
        eventCategoryOne = eventCategoryRepository.save(eventCategoryOne);

        eventCategoryService.delete(eventCategoryOne.getId());

        Assert.assertThat(eventCategoryRepository.findOne(eventCategoryOne.getId()), nullValue());
    }

    @Test(expected = DataIntegrityViolationException.class)
    public void slugShouldBeUniqueTest() {
        EventCategoryDto eventCategoryOne = new EventCategoryDto();
        eventCategoryOne.setName("event");
        eventCategoryOne.setSlug("event");
        EventCategoryDto eventCategoryTwo = new EventCategoryDto();
        eventCategoryTwo.setName("event");
        eventCategoryTwo.setSlug("event");
        eventCategoryService.save(eventCategoryOne);
        eventCategoryService.save(eventCategoryTwo);
    }
}
