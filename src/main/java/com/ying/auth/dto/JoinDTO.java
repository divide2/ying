package com.ying.auth.dto;

import lombok.Data;

import javax.validation.constraints.NotEmpty;

/**
 * @author bvvy
 */
@Data
public class JoinDTO {
    @NotEmpty
    private String password;

    @NotEmpty
    private String account;
}