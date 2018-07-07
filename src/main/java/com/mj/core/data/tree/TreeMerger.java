package com.mj.core.data.tree;

import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * @author bvvy
 */
@Data
@AllArgsConstructor
public class TreeMerger {
    private Integer id;
    private String title;
    private Integer pid;
}
