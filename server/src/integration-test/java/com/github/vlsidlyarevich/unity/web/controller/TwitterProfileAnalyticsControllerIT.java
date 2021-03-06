package com.github.vlsidlyarevich.unity.web.controller;

import com.github.vlsidlyarevich.unity.Application;
import com.github.vlsidlyarevich.unity.common.model.AnalyzedResource;
import com.github.vlsidlyarevich.unity.domain.service.UserService;
import com.github.vlsidlyarevich.unity.web.exception.handler.PersistenceExceptionHandler;
import com.github.vlsidlyarevich.unity.web.exception.handler.SecurityExceptionHandler;
import com.github.vlsidlyarevich.unity.web.exception.handler.TwitterExceptionHandler;
import com.github.vlsidlyarevich.unity.web.security.service.TokenService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpMethod;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.web.context.WebApplicationContext;

import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.notNullValue;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = {Application.class, SecurityExceptionHandler.class,
        PersistenceExceptionHandler.class, TwitterExceptionHandler.class})
@WebAppConfiguration
public class TwitterProfileAnalyticsControllerIT extends AbstractControllerIT {

    @Autowired
    private WebApplicationContext context;

    @Autowired
    private UserService userService;

    @Autowired
    private TokenService tokenService;

    @Value("${security.token.header.name}")
    private String authHeaderName;

    @Before
    public void setUp() {
        prepareTestContextWithAdmin(context);

        userService.create(user);

        this.token = tokenService.getToken(user.getUsername(), user.getPassword());
    }

    @Test
    public void getTwitterProfile_Success_IfLoginPresent() throws Exception {
        mvc.perform(MockMvcRequestBuilders.request(HttpMethod.GET, "/api/v1/twitter/profile/vlsidlyarevich")
                .accept(contentType)
                .contentType(contentType)
                .header(authHeaderName, token))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.id", notNullValue()))
                .andExpect(MockMvcResultMatchers.jsonPath("$.analyzedAt", notNullValue()))
                .andExpect(MockMvcResultMatchers.jsonPath("$.analysisTime", notNullValue()))
                .andExpect(MockMvcResultMatchers.jsonPath("$.resource", is(AnalyzedResource.TWITTER.toString())))
                .andExpect(MockMvcResultMatchers.jsonPath("$.result", notNullValue()));
    }

    @Test
    public void getTwitterProfile_NotFound_IfLoginNotPresent() throws Exception {
        mvc.perform(MockMvcRequestBuilders.request(HttpMethod.GET, "/api/v1/twitter/profile/nosuchloginexists")
                .accept(contentType)
                .contentType(contentType)
                .header(authHeaderName, token))
                .andExpect(MockMvcResultMatchers.status().isNotFound());
    }
}
