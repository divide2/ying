package com.mj.core.er;

import com.mj.core.data.resp.Messager;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

/**
 * @author bvvy
 * 对操作的返回信息
 */
public class Responser {
    private static final Integer UPDATE_STATUE = 209;
    private static final Integer DELETE_STATUS = 210;


    public static <T> ResponseEntity ok(T body) {
        return ResponseEntity.ok(body);
    }

    public static ResponseEntity<Messager> created() {
        return ResponseEntity.status(HttpStatus.CREATED).body(Messager.of("m_save_success"));
    }

    public static ResponseEntity<Messager> updated() {
        return ResponseEntity.status(UPDATE_STATUE).body(Messager.of("m_save_success"));
    }


    public static ResponseEntity<Messager> deleted() {
        return ResponseEntity.status(DELETE_STATUS).body(Messager.of("m_delete_success"));
    }

    public static ResponseEntity<Messager> conflict(String code) {
        return ResponseEntity.status(HttpStatus.CONFLICT).body(Messager.of(code));
    }

}
