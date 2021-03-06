package com.github.vlsidlyarevich.unity.domain.repository;

import com.github.vlsidlyarevich.unity.domain.model.UserSocial;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static com.github.vlsidlyarevich.unity.TestUtils.createUserSocial;

@RunWith(SpringRunner.class)
@SpringBootTest
public class UserSocialRepositoryIT {

    @Autowired
    private UserSocialRepository userSocialRepository;

    @Before
    public void cleanUp() {
        userSocialRepository.deleteAll();
    }

    @Test
    public void findByUserId_Success_ifValid() throws Exception {
        UserSocial userSocial = createUserSocial();

        userSocialRepository.save(userSocial);

        UserSocial savedUserSocial = userSocialRepository.findByUserId(userSocial.getUserId());

        Assert.assertNotNull(savedUserSocial);
        Assert.assertNotNull(savedUserSocial.getId());
        Assert.assertEquals(userSocial.getUserId(), savedUserSocial.getUserId());
    }

    @Test
    public void deleteByUserId_Success_ifValid() throws Exception {
        UserSocial userSocial = createUserSocial();

        userSocialRepository.save(userSocial);

        userSocialRepository.deleteByUserId(userSocial.getUserId());

        Assert.assertNull(userSocialRepository.findByUserId(userSocial.getUserId()));
        Assert.assertEquals(0, userSocialRepository.findAll().size());
    }
}
