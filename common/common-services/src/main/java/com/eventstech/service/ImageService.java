package com.eventstech.service;

import com.eventstech.rest.dto.ImageUploadingDto;
import com.eventstech.service.exception.ImageUploadingException;

/**
 * Date: 20.08.14
 * Author: Ivan Skrypka
 * Copyright © 2014 Statiq, Inc.
 */
public interface ImageService {
    String uploadImage(ImageUploadingDto imageUploadingDto) throws ImageUploadingException;
}
