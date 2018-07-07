package com.mj.core.data.tree;

import lombok.Data;

import java.util.List;

/**
 * @author bvvy
 */
@Data
public class Tree {
    private Integer id;
    private String title;
    private List<Tree> children;
}
