package com.bheternal.jhome.spring.demo;

import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.scheduling.annotation.EnableScheduling;

/**
 * Demo
 *
 * @author Zain
 * @date 2020/9/9
 */
@EnableScheduling
@EnableCaching
@EnableAspectJAutoProxy
@SpringBootApplication
public class Demo {

    public static void main(String[] args) {

    }

}
