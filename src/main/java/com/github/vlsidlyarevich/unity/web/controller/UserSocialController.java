package com.github.vlsidlyarevich.unity.web.controller;

import com.github.vlsidlyarevich.unity.db.domain.UserSocial;
import com.github.vlsidlyarevich.unity.db.service.UserSocialService;
import com.github.vlsidlyarevich.unity.web.dto.UserSocialDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/user/{id}/social")
public class UserSocialController {

    private final UserSocialService service;

    @Autowired
    public UserSocialController(final UserSocialService service) {
        this.service = service;
    }

    @RequestMapping(method = RequestMethod.GET)
    public ResponseEntity getUserSocialData(@PathVariable final String id) {
        final UserSocial userSocial = service.findByUserId(id);
        return new ResponseEntity<>(UserSocialDTO.fromDomain(userSocial), HttpStatus.OK);
    }

    @RequestMapping(method = RequestMethod.PUT)
    public ResponseEntity updateUserSocialData(@PathVariable final String id,
                                               @RequestBody final UserSocialDTO dto) {
        return new ResponseEntity<>(
                UserSocialDTO.fromDomain(service.update(id, UserSocial.fromDTO(dto))), HttpStatus.OK);
    }

    @RequestMapping(method = RequestMethod.DELETE)
    public ResponseEntity deleteUserSocialDataByUserId(@PathVariable final String id) {
        return new ResponseEntity<>(service.deleteByUserId(id), HttpStatus.OK);
    }
}

