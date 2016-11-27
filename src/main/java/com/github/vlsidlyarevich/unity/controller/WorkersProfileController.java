package com.github.vlsidlyarevich.unity.controller;

import com.github.vlsidlyarevich.unity.dto.WorkerProfileDTO;
import com.github.vlsidlyarevich.unity.model.Name;
import com.github.vlsidlyarevich.unity.service.WorkerProfileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/api/workers")
public class WorkersProfileController {

    @Autowired
    private WorkerProfileService service;

    @RequestMapping(method = RequestMethod.GET)
    public ResponseEntity<?> getAllWorkers() {
        return new ResponseEntity<>(service.findAll(), HttpStatus.OK);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public ResponseEntity<?> getWorkerById(@PathVariable Long id) {
        return new ResponseEntity<>(service.find(id), HttpStatus.OK);
    }

    @RequestMapping(method = RequestMethod.POST)
    public ResponseEntity<?> addWorker(@RequestBody WorkerProfileDTO profile) {
        return new ResponseEntity<>(service.create(profile), HttpStatus.CREATED);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.PUT)
    public ResponseEntity<?> updateWorkerById(@PathVariable Long id, @RequestBody WorkerProfileDTO profile) {
        return new ResponseEntity<>(service.update(id, profile), HttpStatus.OK);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    public ResponseEntity<?> deleteWorkerById(@PathVariable Long id) {
        return new ResponseEntity<>(service.delete(id), HttpStatus.OK);
    }
}
