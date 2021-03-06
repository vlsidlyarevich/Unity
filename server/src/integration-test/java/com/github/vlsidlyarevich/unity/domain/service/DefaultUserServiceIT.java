package com.github.vlsidlyarevich.unity.domain.service;

import com.github.vlsidlyarevich.unity.domain.exception.UserNotFoundException;
import com.github.vlsidlyarevich.unity.web.security.model.Authority;
import com.github.vlsidlyarevich.unity.domain.model.User;
import com.github.vlsidlyarevich.unity.domain.repository.UserRepository;
import com.github.vlsidlyarevich.unity.web.security.model.UserAuthentication;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.test.context.junit4.SpringRunner;

import static com.github.vlsidlyarevich.unity.TestUtils.createUser;
import static org.hamcrest.CoreMatchers.nullValue;
import static org.hamcrest.core.Is.is;
import static org.hamcrest.core.IsNull.notNullValue;

@RunWith(SpringRunner.class)
@SpringBootTest
public class DefaultUserServiceIT {

    @Autowired
    private UserService userService;

    @Autowired
    private UserRepository userRepository;

    @Before
    public void setUp() {
        userRepository.deleteAll();

        User currentUser = createUser();
        currentUser.addAuthority(Authority.ROLE_ADMIN);
        SecurityContextHolder.getContext().setAuthentication(new UserAuthentication(currentUser));
    }

    @After
    public void cleanUp() {
        userRepository.deleteAll();
    }

    @Test
    public void create_Success_IfValid() throws Exception {
        User user = createUser();

        userService.create(user);

        Assert.assertThat(userService.findAll().size(), is(1));
        Assert.assertThat(userService.find(user.getId()), is(user));
    }

    @Test(expected = IllegalArgumentException.class)
    public void create_ExceptionThrown_ifNull() throws Exception {
        userService.create(null);
    }

    @Test
    public void find_Success_IfPresent() throws Exception {
        User user = createUser();

        userService.create(user);

        Assert.assertThat(userService.findAll().size(), is(1));
        Assert.assertThat(userService.find(user.getId()), notNullValue());
    }

    @Test(expected = UserNotFoundException.class)
    public void find_ExceptionThrown_IfNotPresent() throws Exception {
        Assert.assertThat(userService.find("id"), nullValue());
    }

    @Test
    public void findByUsername_Success_IfPresent() throws Exception {
        User user = createUser();

        userService.create(user);

        Assert.assertThat(userService.findAll().size(), is(1));
        Assert.assertThat(userService.findByUsername(user.getUsername()), is(user));
    }

    @Test(expected = UserNotFoundException.class)
    public void findByUsername_ExceptionThrown_IfNotPresent() throws Exception {
        Assert.assertThat(userService.find("id"), nullValue());
    }

    @Test
    public void findAll_Success_IfPresent() throws Exception {
        User user = createUser();

        userService.create(user);

        Assert.assertThat(userService.findAll().size(), is(1));
        Assert.assertTrue(userService.findAll().contains(user));
    }

    @Test
    public void findAll_Empty_IfNotPresent() throws Exception {
        Assert.assertTrue(userService.findAll().isEmpty());
    }

    @Test
    public void update_Success_IfPresent() throws Exception {
        User user = createUser();

        userService.create(user);
        user.setUsername("new username");

        userService.update(user.getId(), user);

        Assert.assertThat(userService.find(user.getId()).getUsername(), is(user.getUsername()));
    }

    @Test(expected = IllegalArgumentException.class)
    public void update_ExceptionThrown_ifNull() throws Exception {
        User user = createUser();

        userService.create(user);
        user.setUsername("new username");

        userService.update(user.getId(), null);
    }

    @Test
    public void update_NewCreated_ifNotPresent() throws Exception {
        User user = createUser();

        userService.create(user);
        user.setUsername("new username");

        userService.update("this id does not exist", user);

        Assert.assertFalse(userService.findAll().isEmpty());
    }

    @Test
    public void delete_Success_IfPresent() throws Exception {
        User user = createUser();

        userService.create(user);
        userService.delete(user.getId());

        Assert.assertTrue(userService.findAll().isEmpty());
    }
}
