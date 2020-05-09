package com.example.demo.message;

import java.lang.annotation.*;

@Target({ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface CodeVerify {
    public String value() default "";
}
