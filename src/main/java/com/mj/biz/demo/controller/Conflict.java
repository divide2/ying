package com.mj.biz.demo.controller;

import lombok.Data;

/**
 * @author bvvy
 */
@Data
public class Conflict {

    public Conflict(String code) {
        this.code = code;
    }

    private String code;
}