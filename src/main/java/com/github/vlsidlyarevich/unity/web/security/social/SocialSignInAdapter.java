package com.github.vlsidlyarevich.unity.web.security.social;

import com.github.vlsidlyarevich.unity.web.security.service.TokenService;
import lombok.AllArgsConstructor;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.social.connect.Connection;
import org.springframework.social.connect.web.SignInAdapter;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.context.request.ServletWebRequest;

import javax.servlet.http.Cookie;

@Component
@AllArgsConstructor
public class SocialSignInAdapter implements SignInAdapter {

    private final UserDetailsService userDetailsService;

    private final TokenService tokenService;

    @Override
    public String signIn(final String userId, final Connection<?> connection, final NativeWebRequest request) {
        UserDetails user = userDetailsService.loadUserByUsername(userId);
        UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(
                user,
                null,
                user.getAuthorities());

        SecurityContextHolder.getContext().setAuthentication(authenticationToken);

        String jwt = tokenService.getToken(user.getUsername(), user.getPassword());

        ServletWebRequest servletWebRequest = (ServletWebRequest) request;
        servletWebRequest.getResponse().addCookie(getSocialAuthenticationCookie(jwt));

        return "/#/social-auth";
    }

    private Cookie getSocialAuthenticationCookie(String token) {
        Cookie socialAuthCookie = new Cookie("social-authentication", token);
        socialAuthCookie.setPath("/");
        socialAuthCookie.setMaxAge(10);
        return socialAuthCookie;
    }
}
