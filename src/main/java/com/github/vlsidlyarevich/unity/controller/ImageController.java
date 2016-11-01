package com.github.vlsidlyarevich.unity.controller;

import com.github.vlsidlyarevich.unity.service.StorageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

/**
 * Created by vladislav on 10/11/16.
 */
@RestController
@RequestMapping("/api/images")
public class ImageController {

    @Autowired
    private StorageService storageService;

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public ResponseEntity getImage(@PathVariable String id) {
        Resource resource = storageService.loadAsResource(id);
//        if (resource != null) {
            return new ResponseEntity<>(resource, HttpStatus.OK);
//        } else {
//            return new ResponseEntity<>("http://www.placehold.it/200x150/EFEFEF/AAAAAA&amp;text=no+image", HttpStatus.OK);
//        }
    }

    @RequestMapping(value = "/upload", method = RequestMethod.POST)
    public ResponseEntity uploadImage(@RequestParam("file") MultipartFile file) {
        return new ResponseEntity<>(storageService.store(file), HttpStatus.OK);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    public ResponseEntity deleteImage(@PathVariable String id) {
        return new ResponseEntity<>(storageService.delete(id), HttpStatus.OK);
    }
}
