package com.github.vlsidlyarevich.unity.web.security.social.controller;

import com.github.vlsidlyarevich.unity.web.security.social.service.SocialSignupService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.view.RedirectView;

@RestController
@AllArgsConstructor
@RequestMapping("/social")
public class SocialController {

    private final SocialSignupService signupService;

    @GetMapping("/signup")
    public RedirectView signUp(final WebRequest webRequest, @CookieValue(name = "NG_TRANSLATE_LANG_KEY",
            required = false, defaultValue = "\"en\"") final String language) {
        return signupService.signup(webRequest, language);
    }
}
