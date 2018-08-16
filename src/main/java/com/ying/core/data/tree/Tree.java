package com.ying.core.data.tree;

import lombok.Data;

import java.util.List;

/**
 * @author bvvy
 */
@Data
public class Tree implements Comparable<Tree> {
    private Integer id;
    private String label;
    private List<Tree> children;
    private String path;
    private Integer orderNum;

    @Override
    public int compareTo(Tree tree) {
        if (this.orderNum > tree.orderNum) {
            return 1;
        } else if (this.orderNum < tree.orderNum) {
            return -1;
        } else {
            return 0;
        }
    }
}
