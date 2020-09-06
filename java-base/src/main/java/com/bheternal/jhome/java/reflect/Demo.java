package com.bheternal.jhome.java.reflect;

import java.lang.annotation.Annotation;
import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.time.LocalDateTime;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * Demo
 *
 * @author Zain
 * @date 2020/9/6
 */
public class Demo {

    public static void main(String[] args) throws Exception {
        getFromClass();
        useReflect();
    }

    private static void useReflect() throws IllegalAccessException {
        User user = new User();
        Field[] fields = User.class.getFields();
        for (Field field : fields) {
            System.out.printf("1. field: %s \n", field);
            field.set(user, field.getName());
        }

        System.out.printf("1. user: %s \n", user);
    }


    private static void getFromClass() {
        // field
        Field[] fields = User.class.getFields();
        Field[] declaredFields = User.class.getDeclaredFields();
        System.out.printf("1. fields: %s \n", Stream.of(fields).map(Field::getName).collect(Collectors.joining(",")));
        System.out.printf("2. declaredFields: %s \n", Stream.of(declaredFields).map(Field::getName).collect(Collectors.joining(",")));

        // method
        Method[] methods = User.class.getMethods();
        Method[] declaredMethods = User.class.getDeclaredMethods();
        Method enclosingMethod = User.class.getEnclosingMethod();
        Method enclosingMethod1 = new Object() {
        }.getClass().getEnclosingMethod();

        System.out.println(enclosingMethod);
        System.out.println(enclosingMethod1);

        // constructor
        Constructor<?>[] constructors = User.class.getConstructors();
        Constructor<?>[] declaredConstructors = User.class.getDeclaredConstructors();
        Constructor<?> enclosingConstructor = User.class.getEnclosingConstructor();


        // annotation
        Annotation[] annotations = User.class.getAnnotations();
        Annotation[] declaredAnnotations = User.class.getDeclaredAnnotations();


        // enum
        Finger[] enumConstants = Finger.class.getEnumConstants();
        System.out.printf("4. enumConstants: %s %s \n", enumConstants[0], enumConstants[1]);

    }

    public enum Finger {
        LITTLE,
        BIG,
        MIDDLE,
    }

    public static class Person {
        public String personPublic;
        private String head;

        public String getHead() {
            return head;
        }

        public void setHead(String head) {
            this.head = head;
        }
    }

    public static class User extends Person {
        public String userPublic;
        private String name;
        private Integer age;
        private LocalDateTime createTime;

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public Integer getAge() {
            return age;
        }

        public void setAge(Integer age) {
            this.age = age;
        }

        public LocalDateTime getCreateTime() {
            return createTime;
        }

        public void setCreateTime(LocalDateTime createTime) {
            this.createTime = createTime;
        }

        @Override
        public String toString() {
            return "User{" +
                    "head='" + super.head + '\'' +
                    ", personPublic='" + personPublic + '\'' +
                    ", name='" + name + '\'' +
                    ", age=" + age +
                    ", createTime=" + createTime +
                    ", userPublic='" + userPublic + '\'' +
                    '}';
        }
    }

}
