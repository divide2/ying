package com.divide2.core.root.query;

import com.querydsl.core.types.Ops;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @author bvvy
 * @date 2018/12/20
 */
@Retention(value = RetentionPolicy.RUNTIME)
@Target(ElementType.FIELD)
public @interface QueryField {

    String value() default "";

    Ops op() default Ops.EQ;

    Class<?> entity();


}
