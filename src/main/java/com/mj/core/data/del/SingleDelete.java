package com.mj.core.data.del;

import lombok.Data;

import javax.validation.constraints.NotNull;

/**
 * @author bvvy
 */
@Data
public class SingleDelete {

    @NotNull
    private Integer id;
}
