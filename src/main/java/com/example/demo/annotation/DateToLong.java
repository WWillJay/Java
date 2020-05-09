package com.example.demo.annotation;

import java.lang.annotation.*;

@Target(value= ElementType.FIELD)
@Retention(value = RetentionPolicy.RUNTIME)
@Documented
public @interface DateToLong {
}
