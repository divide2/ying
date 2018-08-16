package com.ying.core.data.del;

import lombok.Data;

import java.util.List;

/**
 * @author bvvy
 */
@Data
public class MultiDelete<T> {

    private List<T> ids;
}
