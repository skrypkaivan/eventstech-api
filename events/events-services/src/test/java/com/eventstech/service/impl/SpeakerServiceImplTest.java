package com.eventstech.service.impl;

import com.google.common.collect.Sets;
import com.eventstech.db.entity.Speaker;
import com.eventstech.db.entity.SpeakerCategory;
import com.eventstech.db.repositories.SpeakerCategoryRepository;
import com.eventstech.db.repositories.SpeakerRepository;
import com.eventstech.rest.dto.SpeakerCategoryDto;
import com.eventstech.rest.dto.SpeakerDto;
import com.eventstech.service.SpeakerService;
import com.eventstech.test.db.AbstractDbTest;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;

import java.util.Arrays;

import static org.hamcrest.core.IsEqual.equalTo;
import static org.hamcrest.core.IsNull.notNullValue;
import static org.hamcrest.core.IsNull.nullValue;

/**
 * Created by vanish on 8/7/14.
 */
@ContextConfiguration(locations = {"classpath*:spring/spring-events-services-test-context.xml"})
@Ignore
public class SpeakerServiceImplTest extends AbstractDbTest {

    @Autowired
    private SpeakerService speakerService;
    @Autowired
    private SpeakerRepository speakerRepository;
    @Autowired
    private SpeakerCategoryRepository speakerCategoryRepository;

    private SpeakerCategory category;

    @Before
    public void prePopulateCategories() {
        category = new SpeakerCategory();
        category.setName("First");
        category.setSlug("first");
        speakerCategoryRepository.save(category);
    }

    @Test
    public void saveTest() {
        SpeakerDto speaker = new SpeakerDto();
        speaker.setSlug("Slug");
        speaker.setName("Ivan Skrypka");
        speaker.setLongDescription("Long desc");
        speaker.setPhoto("/home/vanish/photo.png");
        speaker.setShortDescription("short desc");
        SpeakerCategoryDto speakerCategory = new SpeakerCategoryDto();
        speakerCategory.setId(category.getId());
        speaker.setTags(Arrays.asList(speakerCategory));
        speaker = speakerService.save(speaker);

        Assert.assertThat(speakerRepository.findOne(speaker.getId()), notNullValue());
    }

    @Test
    public void getPageTest() {
        Speaker speaker = new Speaker();
        speaker.setSlug("Slug");
        speaker.setName("Ivan Skrypka");
        speaker.setLongDescription("Long desc");
        speaker.setPhoto("/home/vanish/photo.png");
        speaker.setShortDescription("short desc");
        SpeakerCategory speakerCategory = new SpeakerCategory();
        speakerCategory.setId(category.getId());
        speaker.setCategories(Sets.newHashSet(speakerCategory));
        speakerRepository.save(speaker);

        Assert.assertThat(speakerService.getPage(1, 1).size(), equalTo(1));
    }

    @Test
    public void deleteTest() {
        Speaker speaker = new Speaker();
        speaker.setSlug("Slug");
        speaker.setName("Ivan Skrypka");
        speaker.setLongDescription("Long desc");
        speaker.setPhoto("/home/vanish/photo.png");
        speaker.setShortDescription("short desc");
        SpeakerCategory speakerCategory = new SpeakerCategory();
        speakerCategory.setId(category.getId());
        speaker.setCategories(Sets.newHashSet(speakerCategory));
        speaker = speakerRepository.save(speaker);

        speakerService.delete(speaker.getId());

        Assert.assertThat(speakerRepository.findOne(speaker.getId()), nullValue());
    }

    @Test
    public void getSpeakerBySlugTest() {
        Speaker speaker = new Speaker();
        speaker.setSlug("Slug");
        speaker.setName("Ivan Skrypka");
        speaker.setLongDescription("Long desc");
        speaker.setPhoto("/home/vanish/photo.png");
        speaker.setShortDescription("short desc");
        SpeakerCategory speakerCategory = new SpeakerCategory();
        speakerCategory.setId(category.getId());
        speaker.setCategories(Sets.newHashSet(speakerCategory));
        speakerRepository.save(speaker);

        Assert.assertThat(speakerService.getSpeakerBySlug(speaker.getSlug()), notNullValue());
    }

    @Test
    public void getByTagSlugTest() {
        Speaker speaker = new Speaker();
        speaker.setSlug("Slug");
        speaker.setName("Ivan Skrypka");
        speaker.setLongDescription("Long desc");
        speaker.setPhoto("/home/vanish/photo.png");
        speaker.setShortDescription("short desc");
        SpeakerCategory speakerCategory = new SpeakerCategory();
        speakerCategory.setId(category.getId());
        speaker.setCategories(Sets.newHashSet(speakerCategory));
        speakerRepository.save(speaker);

        Assert.assertThat(speakerService.getByTagSlug(category.getSlug(),1,1).size(), equalTo(1));
    }

    @Test
    public void getPopularSpeakerFound() {
        Speaker speaker = new Speaker();
        speaker.setSlug("Slug");
        speaker.setName("Ivan Skrypka");
        speaker.setLongDescription("Long desc");
        speaker.setPhoto("/home/vanish/photo.png");
        speaker.setShortDescription("short desc");
        speaker.setPopular(true);
        SpeakerCategory speakerCategory = new SpeakerCategory();
        speakerCategory.setId(category.getId());
        speaker.setCategories(Sets.newHashSet(speakerCategory));
        speakerRepository.save(speaker);

        Assert.assertThat(speakerService.getPopularSpeakers(1).size(), equalTo(1));
    }

    @Test
    public void getPopularSpeakerNotFound() {
        Speaker speaker = new Speaker();
        speaker.setSlug("Slug");
        speaker.setName("Ivan Skrypka");
        speaker.setLongDescription("Long desc");
        speaker.setPhoto("/home/vanish/photo.png");
        speaker.setShortDescription("short desc");
        SpeakerCategory speakerCategory = new SpeakerCategory();
        speakerCategory.setId(category.getId());
        speaker.setCategories(Sets.newHashSet(speakerCategory));
        speakerRepository.save(speaker);

        Assert.assertThat(speakerService.getPopularSpeakers(1).size(), equalTo(0));
    }
}
