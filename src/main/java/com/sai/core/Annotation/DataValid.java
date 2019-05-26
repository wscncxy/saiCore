package com.sai.core.Annotation;

import com.sai.core.utils.ValidEnum;
import com.sai.core.utils.Validate;

import java.lang.annotation.*;

@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
public @interface DataValid {
    String name() default "";

    boolean noNull() default false;

    String maxVal() default "";

    String minVal() default "";

    long maxLen() default -1L;

    long minLen() default -1L;

    Class<ValidEnum> valildEnum() default ValidEnum.class;

    Class<Validate> valild() default Validate.class;
}
