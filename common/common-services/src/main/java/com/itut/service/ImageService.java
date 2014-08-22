package com.itut.service;

import com.itut.rest.dto.ImageUploadingDto;
import com.itut.service.exception.ImageUploadingException;
import org.springframework.web.multipart.MultipartFile;

/**
 * Date: 20.08.14
 * Author: Ivan Skrypka
 * Copyright © 2014 Statiq, Inc.
 */
public interface ImageService {
    String uploadImage(ImageUploadingDto imageUploadingDto) throws ImageUploadingException;
}
