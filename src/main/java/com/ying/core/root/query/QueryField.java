package com.ying.core.root.query;

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

    String[] value();

    OP op();

    public enum OP {
        EQ,LIKE
    }
}
