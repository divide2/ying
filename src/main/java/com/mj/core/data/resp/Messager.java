package com.mj.core.data.resp;

/**
 * @author bvvy
 */
public class Messager {

    private String code ;

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public Messager(String code) {
        this.code = code;
    }

    public static Messager of(String code) {
        return new Messager(code);

    }


}
