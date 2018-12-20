package com.ying.core.root.dto.anno;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @author bvvy
 * @date 2018/12/20
 */
@Retention(value = RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
public @interface RelateTable {

    Class<?> entity();

    String alias() default "";

    String joinType() default "";
}
