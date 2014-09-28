package com.eventstech.property;

import com.eventstech.rest.dto.ImageUploadingDto;

import java.beans.PropertyEditorSupport;

/**
 * Created by vanish on 8/21/14.
 */
public class ImageUploadingTypePropertyEditor extends PropertyEditorSupport {

    @Override
    public void setAsText(String typeString){
        for (ImageUploadingDto.Type type: ImageUploadingDto.Type.values()) {
            if (type.getTypeName().equals(typeString)) {
                setValue(type);
            }
        }
    }
}
