package com.itut.service;

import com.itut.service.exception.ImageUploadingException;
import org.springframework.web.multipart.MultipartFile;

/**
 * Date: 20.08.14
 * Author: Ivan Skrypka
 * Copyright © 2014 Statiq, Inc.
 */
public interface ImageService {
    void uploadImage(MultipartFile image, String imageName, String folder) throws ImageUploadingException;
}
