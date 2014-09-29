package com.eventstech.service.impl;

import com.eventstech.rest.dto.ImageUploadingDto;
import com.eventstech.service.ImageService;
import com.eventstech.service.exception.ImageUploadingException;
import com.google.common.base.Joiner;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.io.FilenameUtils;
import org.joda.time.DateTime;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/**
 * Author: Ivan Skrypka
 * Copyright © 2014 Eventstech.com.ua.
 */
@Slf4j
public class ImageServiceImpl implements ImageService {

    private Map<ImageUploadingDto.Type, String> imageType2UploadingFolder = new HashMap<>();
    private Map<ImageUploadingDto.Type, String> imageType2Url = new HashMap<>();

    @Override
    public String uploadImage(ImageUploadingDto imageUploadingDto) throws ImageUploadingException {
        String extension = FilenameUtils.getExtension(imageUploadingDto.getImage().getOriginalFilename());
        String imageName = getImageFinalFileName(imageUploadingDto, extension);
        try {
            BufferedImage src = ImageIO.read(imageUploadingDto.getImage().getInputStream());
            String folderToUpload = imageType2UploadingFolder.get(imageUploadingDto.getType());
            ImageIO.write(src, extension, getImageDestination(imageName, folderToUpload));
            return Joiner.on("/").join(Arrays.asList(imageType2Url.get(imageUploadingDto.getType()), imageName));
        } catch (IOException e) {
            log.error("Error during image uploading. Image name = {}", imageName);
            throw new ImageUploadingException(String.format("Error during image uploading. Image name = %s", imageName), e);
        }
    }

    private String getImageFinalFileName(ImageUploadingDto imageUploadingDto, String extension) {
        return FilenameUtils.getBaseName(imageUploadingDto.getImage().getOriginalFilename()) + "_" + DateTime.now().getMillis() + "." + extension;
    }

    private File getImageDestination(String imageName, String folder) throws IOException {
        return new File(Joiner.on(File.separator).join(Arrays.asList(folder, imageName)));
    }

    public void setImageType2UploadingFolder(Map<ImageUploadingDto.Type, String> imageType2UploadingFolder) {
        this.imageType2UploadingFolder = imageType2UploadingFolder;
    }

    public void setImageType2Url(Map<ImageUploadingDto.Type, String> imageType2Url) {
        this.imageType2Url = imageType2Url;
    }
}
