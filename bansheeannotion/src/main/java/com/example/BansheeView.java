package com.example;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Created by Carry on 16/8/9.
 */
@Retention(RetentionPolicy.CLASS)
//注解成员变量
@Target(ElementType.FIELD)
public @interface BansheeView {
    int  value();
}
