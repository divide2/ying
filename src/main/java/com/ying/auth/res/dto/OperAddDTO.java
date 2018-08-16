package com.ying.auth.res.dto;

import lombok.Data;

import java.util.List;

/**
 * @author bvvy
 */
@Data
public class OperAddDTO {

    private String name;
    private Integer resId;
    private String code;
    private Integer indexPos;
    private List<Integer> apis;
}
