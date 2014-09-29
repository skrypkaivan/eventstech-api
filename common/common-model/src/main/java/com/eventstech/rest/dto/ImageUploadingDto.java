package com.eventstech.rest.dto;

import lombok.Getter;
import lombok.Setter;
import org.springframework.web.multipart.MultipartFile;

/**
 * Author: Ivan Skrypka
 * Copyright © 2014 Eventstech.com.ua.
 */
public class ImageUploadingDto {

    public static enum Type {
        EVENT("event"),
        SPEAKER("speaker");

        private String typeName;

        Type(String typeName) {
            this.typeName = typeName;
        }

        public String getTypeName() {
            return typeName;
        }
    }

    @Getter @Setter private MultipartFile image;
    @Getter @Setter private Type type;
}
