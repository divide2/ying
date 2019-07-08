package com.divide2.core.data.resp;

import lombok.Data;

/**
 * @author bvvy
 */
@Data
public class Messager {

    private String code;
    private String message ;

    public Messager(String message) {
        this.message = message;
    }

    public static Messager success() {
        Messager success = Messager.of("success");
        success.setCode("SS001");
        return success;
    }

    public static Messager success(String code) {
        Messager success = Messager.of("success");
        success.setCode(code);
        return success;
    }


    public static Messager fail(String code,String message) {
        Messager fail = Messager.of(message);
        fail.setCode(code);
        return fail;
    }

    public static Messager of(String message) {
        return new Messager(message);

    }


}
