package com.github.vlsidlyarevich.unity.web.controller;

import com.github.vlsidlyarevich.unity.web.security.facade.AuthenticationFacade;
import com.github.vlsidlyarevich.unity.db.domain.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/user")
public class UserController {

    private final AuthenticationFacade authenticationFacade;

    @Autowired
    public UserController(final AuthenticationFacade authenticationFacade) {
        this.authenticationFacade = authenticationFacade;
    }

    @RequestMapping(method = RequestMethod.GET)
    public ResponseEntity getCurrentUser() {
        final User currentUser =
                (User) authenticationFacade.getAuthentication().getDetails();
        return new ResponseEntity<>(currentUser, HttpStatus.OK);
    }
}