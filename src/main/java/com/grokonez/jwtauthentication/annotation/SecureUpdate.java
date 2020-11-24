package com.grokonez.jwtauthentication.annotation;

import java.lang.annotation.*;

@Documented
@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
public @interface SecureUpdate {
    String[] value();
}
