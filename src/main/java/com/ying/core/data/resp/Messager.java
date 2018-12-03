package com.ying.core.data.resp;

import lombok.Data;

/**
 * @author bvvy
 */
@Data
public class Messager {

    private String message ;

    public Messager(String message) {
        this.message = message;
    }

    public static Messager of(String message) {
        return new Messager(message);

    }


}
