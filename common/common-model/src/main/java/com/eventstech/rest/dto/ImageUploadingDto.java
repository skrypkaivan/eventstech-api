package com.eventstech.rest.dto;

import org.springframework.web.multipart.MultipartFile;

/**
 * Created by vanish on 8/21/14.
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
    private MultipartFile image;
    private Type type;

    public MultipartFile getImage() {
        return image;
    }

    public void setImage(MultipartFile image) {
        this.image = image;
    }

    public Type getType() {
        return type;
    }

    public void setType(Type type) {
        this.type = type;
    }
}
