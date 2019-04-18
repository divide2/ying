package com.divide2.core.data.del;

import lombok.Data;

import javax.validation.constraints.NotNull;

/**
 * @author bvvy
 */
@Data
public class SingleId {

    @NotNull
    private Integer id;
}
