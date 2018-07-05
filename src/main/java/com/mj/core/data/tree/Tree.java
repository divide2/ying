package com.mj.core.data.tree;

import lombok.Data;

import java.util.List;

/**
 * @author bvvy
 */
@Data
public class Tree<ID, P> {
    private ID id;
    private String title;
    private P payload;
    private List<Tree> children;
}
