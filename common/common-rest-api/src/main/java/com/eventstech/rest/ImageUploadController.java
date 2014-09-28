package com.eventstech.rest;

import com.eventstech.property.ImageUploadingTypePropertyEditor;
import com.eventstech.rest.dto.ImageUploadingDto;
import com.eventstech.service.ImageService;
import com.eventstech.service.exception.ImageUploadingException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartHttpServletRequest;

/**
 * Created by vanish on 8/21/14.
 */
@RestController
public class ImageUploadController {

    @Autowired
    private ImageService imageService;

    @RequestMapping(value = "/upload", consumes = MediaType.MULTIPART_FORM_DATA_VALUE, method = RequestMethod.POST, produces = MediaType.TEXT_PLAIN_VALUE)
    public ResponseEntity<String> uploadImage(@ModelAttribute("imageUploadingDto") ImageUploadingDto imageUploadingDto) {
        try {
            return new ResponseEntity<>(imageService.uploadImage(imageUploadingDto), HttpStatus.OK);
        } catch (ImageUploadingException e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @InitBinder
    public void initBinder(WebDataBinder binder) {
        binder.registerCustomEditor(ImageUploadingDto.Type.class, new ImageUploadingTypePropertyEditor());
    }

    @ModelAttribute("imageUploadingDto")
    public ImageUploadingDto getImageUploadingDto(MultipartHttpServletRequest req) {
        ImageUploadingDto imageUploadingDto = new ImageUploadingDto();
        imageUploadingDto.setImage(req.getFile("Filedata"));
        return imageUploadingDto;
    }
}
