package com.divide2.core.er;

import com.divide2.core.data.resp.Messager;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

/**
 * @author bvvy
 * 对操作的返回信息
 */
public class Responser{

    private static final Integer UPDATE_STATUE = 209;
    private static final Integer DELETE_STATUS = 210;


    public static <T> ResponseEntity<T> ok(T body) {
        return ResponseEntity.ok(body);
    }

    public static ResponseEntity<Messager> created() {
        return ResponseEntity.status(HttpStatus.CREATED).body(Messager.success());
    }

    public static ResponseEntity<Messager> updated() {
        return ResponseEntity.status(UPDATE_STATUE).body(Messager.success());
    }


    public static ResponseEntity<Messager> deleted() {
        return ResponseEntity.status(DELETE_STATUS).body(Messager.success());
    }

    public static ResponseEntity<Messager> conflict(String code) {
        return ResponseEntity.status(HttpStatus.CONFLICT).body(Messager.fail(code, "fail"));
    }

}
