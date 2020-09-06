package com.aizain.jhome.spring.source.app.service.impl;

import com.aizain.jhome.spring.source.app.po.User;
import com.aizain.jhome.spring.source.app.service.UserService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import static com.aizain.jhome.spring.source.app.common.SpringContext.APPLICATION_CONTEXT;
import static com.aizain.jhome.spring.source.app.common.SpringContext.NEW_USER;

/**
 * UserServiceImplTest
 *
 * @author Zain
 * @date 2019-05-24
 */
class UserServiceImplTest {

    @Test
    void findUserById() {

        // by type
        UserService userService = APPLICATION_CONTEXT.getBean(UserService.class);
        User user = userService.findUserById(1);

        Assertions.assertNotNull(user);
        Assertions.assertEquals(NEW_USER.getId(), user.getId());

        // by name
        userService = APPLICATION_CONTEXT.getBean("UserService", UserService.class);
        user = userService.findUserById(1);

        Assertions.assertNotNull(user);
        Assertions.assertEquals(NEW_USER.getId(), user.getId());

    }

}