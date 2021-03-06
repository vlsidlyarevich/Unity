package com.github.vlsidlyarevich.unity.domain.service;

import com.github.vlsidlyarevich.unity.domain.model.User;
import com.github.vlsidlyarevich.unity.domain.exception.UserNotFoundException;
import com.github.vlsidlyarevich.unity.domain.exception.UsernameExistsException;
import com.github.vlsidlyarevich.unity.domain.assistant.DefaultUserAssistant;
import com.github.vlsidlyarevich.unity.domain.repository.UserRepository;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.Collections;

import static com.github.vlsidlyarevich.unity.TestUtils.createUser;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.Matchers.containsInAnyOrder;
import static org.hamcrest.collection.IsEmptyCollection.emptyCollectionOf;
import static org.junit.Assert.assertThat;
import static org.mockito.Matchers.anyObject;
import static org.mockito.Matchers.anyString;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.verify;

@RunWith(SpringRunner.class)
public class DefaultUserServiceTest {

    private UserService userService;

    @Mock
    private UserRepository userRepository;

    @Mock
    private UserSocialService userSocialService;

    @Mock
    private UserAnalyticsService userAnalyticsService;

    @Before
    public void setUp() {
        this.userService = new DefaultUserService(userRepository, userSocialService, userAnalyticsService, new DefaultUserAssistant(userRepository));
    }

    @Test
    public void create_Success_IfValid() throws Exception {
        User user = createUser();

        doReturn(user).when(userRepository).save((User) anyObject());

        userService.create(user);

        verify(userRepository).save(user);
    }

    @Test(expected = IllegalArgumentException.class)
    public void create_ExceptionThrown_ifNull() throws Exception {
        userService.create(null);
    }

    @Test(expected = UsernameExistsException.class)
    public void create_ExceptionThrown_ifUsernameExists() throws Exception {
        User user = createUser();

        doReturn(new ArrayList<User>() {{
            add(user);
        }}).when(userRepository).findAll();
        doReturn(user).when(userRepository).save((User) anyObject());

        userService.create(user);
    }


    @Test
    public void find_Success_IfPresent() throws Exception {
        User user = createUser();

        doReturn(user).when(userRepository).findOne(user.getId());

        assertThat(userService.find(user.getId()), is(user));

        verify(userRepository).findOne(user.getId());
    }

    @Test(expected = UserNotFoundException.class)
    public void find_Null_IfNotPresent() throws Exception {
        doReturn(null).when(userRepository).findOne(anyString());

        userService.find("id");
    }

    @Test
    public void findByUsername_Success_IfPresent() throws Exception {
        User user = createUser();

        doReturn(user).when(userRepository).findByUsername(user.getUsername());

        assertThat(userService.findByUsername(user.getUsername()), is(user));

        verify(userRepository).findByUsername(user.getUsername());
    }

    @Test(expected = UserNotFoundException.class)
    public void findByUsername_Null_IfNotPresent() throws Exception {
        doReturn(null).when(userRepository).findByUsername(anyString());

        userService.findByUsername("username");
    }

    @Test
    public void findAll_Success_IfPresent() throws Exception {
        User user = createUser();
        ArrayList userList = new ArrayList() {{
            add(user);
        }};

        doReturn(userList).when(userRepository).findAll();

        assertThat(userService.findAll(), containsInAnyOrder(userList.toArray()));

        verify(userRepository).findAll();
    }

    @Test
    public void findAll_Empty_IfNotPresent() throws Exception {
        doReturn(Collections.emptyList()).when(userRepository).findAll();

        assertThat(userService.findAll(), emptyCollectionOf(User.class));

        verify(userRepository).findAll();
    }

    @Test
    public void update_Success_IfPresent() throws Exception {
        User user = createUser();

        doReturn(user).when(userRepository).findOne(user.getId());
        doReturn(user).when(userRepository).save((User) anyObject());

        userService.update(user.getId(), user);

        verify(userRepository).save(user);
    }

    @Test(expected = IllegalArgumentException.class)
    public void update_ExceptionThrown_ifNull() throws Exception {
        User user = createUser();

        doReturn(null).when(userRepository).findOne(user.getId());

        userService.update(user.getId(), null);
    }

    @Test
    public void update_NewCreated_ifNotPresent() throws Exception {
        User user = createUser();

        doReturn(user).when(userRepository).findOne(user.getId());
        doReturn(user).when(userRepository).save((User) anyObject());

        userService.update("not present", user);

        verify(userRepository).save(user);
    }

    @Test
    public void delete_Success_IfPresent() throws Exception {
        User user = createUser();

        userService.delete(user.getId());

        verify(userRepository).delete(user.getId());
    }
}
