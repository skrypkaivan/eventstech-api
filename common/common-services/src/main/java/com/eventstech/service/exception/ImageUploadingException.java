package com.eventstech.service.exception;

/**
 * Author: Ivan Skrypka
 * Copyright © 2014 Eventstech.com.ua.
 */
public class ImageUploadingException extends Exception {

    public ImageUploadingException(String message, Exception e) {
        super(message, e);
    }
}
