package com.eventstech.service;

import com.eventstech.rest.dto.ImageUploadingDto;
import com.eventstech.service.exception.ImageUploadingException;

/**
 * Author: Ivan Skrypka
 * Copyright © 2014 Eventstech.com.ua.
 */
public interface ImageService {
    String uploadImage(ImageUploadingDto imageUploadingDto) throws ImageUploadingException;
}
