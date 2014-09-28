package com.eventstech.service.exception;

/**
 * Date: 20.08.14
 * Author: Ivan Skrypka
 * Copyright © 2014 Statiq, Inc.
 */
public class ImageUploadingException extends Exception {

    public ImageUploadingException(String message, Exception e) {
        super(message, e);
    }
}
