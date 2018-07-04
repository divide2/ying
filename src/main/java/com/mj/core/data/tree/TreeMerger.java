package com.mj.core.data.tree;

import lombok.Data;

@Data
public class TreeMerger<ID, P> {
    private ID id;
    private String title;
    private ID pid;
    private P payload;
}
