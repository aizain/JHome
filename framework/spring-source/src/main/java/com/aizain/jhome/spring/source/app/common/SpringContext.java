package com.aizain.jhome.spring.source.app.common;

import com.aizain.jhome.spring.source.app.po.User;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.time.LocalDateTime;
import java.util.UUID;

/**
 * SpringContext
 *
 * @author Zain
 * @date 2019-05-24
 */
public class SpringContext {

    private static final String APPLICATION_CONTEXT_FILE = "application-context.xml";
    public static final ApplicationContext APPLICATION_CONTEXT = createContext();
    private static final String NEW_USER_NAME = UUID.randomUUID().toString().replaceAll("-", "");
    public static final User NEW_USER = createUser();

    private static User createUser() {
        User user = new User();
        user.setName(NEW_USER_NAME);
        user.setCreateTime(LocalDateTime.now());
        user.setUpdateTime(LocalDateTime.now());
        return user;
    }

    private static ApplicationContext createContext() {
        return new ClassPathXmlApplicationContext(APPLICATION_CONTEXT_FILE);
    }

}
