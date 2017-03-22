package com.github.vlsidlyarevich.unity.git.service.impl;

import com.github.vlsidlyarevich.unity.git.config.GitProperties;
import com.github.vlsidlyarevich.unity.git.factory.RestTemplateFactory;
import com.github.vlsidlyarevich.unity.git.model.GitProfile;
import com.github.vlsidlyarevich.unity.git.service.GitProfileService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

import javax.annotation.PostConstruct;
import java.util.Optional;


@Service
public class GitProfileServiceImpl implements GitProfileService {

    private static final Logger logger = LoggerFactory.getLogger(GitProfileServiceImpl.class);

    private final String gitApiUrl;

    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    private RestTemplateFactory restTemplateFactory;

    @Autowired
    public GitProfileServiceImpl(GitProperties gitProperties) {
        this.gitApiUrl = gitProperties.getApiUrl() + "/users/{user}";
    }

    @PostConstruct
    public void init() {
        this.restTemplate = restTemplateFactory.getObject();
    }

    public Optional<GitProfile> getGitProfile(String gitProfile) {
        Optional<GitProfile> result;
        try {
            result = Optional.of(restTemplate.getForObject(gitApiUrl, GitProfile.class, gitProfile));
        } catch (HttpClientErrorException e) {
            result = Optional.empty();
            logger.error("Can't find git profile: {gitProfile}", gitProfile);
        }
        return result;
    }
}