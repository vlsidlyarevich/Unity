package com.github.vlsidlyarevich.unity.web.controller;

import com.github.vlsidlyarevich.unity.db.service.UserService;
import com.github.vlsidlyarevich.unity.web.converter.ConverterFacade;
import com.github.vlsidlyarevich.unity.web.dto.UserDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/signup")
public class SignUpController {

    @Autowired
    private UserService service;

    @Autowired
    private ConverterFacade converterFacade;

    @RequestMapping(method = RequestMethod.POST)
    public ResponseEntity<?> signUp(@RequestBody UserDTO dto) {
        return new ResponseEntity<>(service.create(converterFacade.convert(dto)), HttpStatus.OK);
    }
}
