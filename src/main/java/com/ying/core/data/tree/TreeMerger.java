package com.ying.core.data.tree;

import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * @author bvvy
 */
@Data
@AllArgsConstructor
public class TreeMerger {
    private Integer id;
    private String label;
    private Integer pid;
    private String path;
    private Integer orderNum;
}
