package com.itut.service.impl;

import com.google.common.base.Joiner;
import com.itut.service.ImageService;
import com.itut.service.exception.ImageUploadingException;
import org.apache.commons.io.FilenameUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.multipart.MultipartFile;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.nio.file.NotDirectoryException;
import java.util.Arrays;

/**
 * Date: 20.08.14
 * Author: Ivan Skrypka
 * Copyright © 2014 Statiq, Inc.
 */
public class ImageServiceImpl implements ImageService {

    private static final Logger LOG = LoggerFactory.getLogger(ImageServiceImpl.class);

    @Override
    public void uploadImage(MultipartFile image, String imageName, String folder) throws ImageUploadingException {
        try {
            BufferedImage src = ImageIO.read(image.getInputStream());
            ImageIO.write(src, FilenameUtils.getExtension(imageName), getImageDestination(imageName, folder));
        } catch (IOException e) {
            LOG.error("Error during image uploading. Image name = {}, folder = {}", imageName, folder);
            throw new ImageUploadingException(String.format("Error during image uploading. Image name = %s, folder = %s", imageName, folder), e);
        }
    }

    private File getImageDestination(String imageName, String folder) throws IOException {
        File imageFolder = new File(folder);
        if (!imageFolder.exists()) {
            imageFolder.mkdirs();
        }
        if (!imageFolder.isDirectory()) {
            LOG.info("Destination {}, for image {} is not a folder", folder, imageName);
            throw new NotDirectoryException(String.format("Destination %s, for image %s is not a folder", folder, imageName));
        }
        return new File(Joiner.on(File.pathSeparator).join(Arrays.asList(folder, imageName)));
    }
}
