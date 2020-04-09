package com.sai.core.annotation;

import com.sai.core.constants.enums.BaseEnum;
import com.sai.core.utils.Validate;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
public @interface DataValid {
    String name() default "";

    boolean notNull() default false;

    boolean notBlank() default false;

    String maxVal() default "";

    String minVal() default "";

    int maxLen() default -1;

    int minLen() default -1;

}
