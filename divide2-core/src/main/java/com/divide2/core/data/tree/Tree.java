package com.divide2.core.data.tree;

import com.divide2.core.data.tree.payload.Payload;
import lombok.Data;

import java.util.List;

/**
 * @author bvvy
 */
@Data
public class Tree<P extends Payload> implements Comparable<Tree<P>> {
    private Integer id;
    private String label;
    private List<Tree<P>> children;
    private Integer orderNum;
    private P payload;


    @Override
    public int compareTo(Tree<P> o) {
        if (this.orderNum > o.getOrderNum()) {
            return 1;
        } else if (this.orderNum < o.getOrderNum()) {
            return -1;
        } else {
            return 0;
        }
    }


}
