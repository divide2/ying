package com.ying.core.data.tree;

import com.ying.core.data.tree.payload.Payload;

import java.util.List;

/**
 * @author bvvy
 * @date 2018/9/18
 */
public interface ITree<P extends Payload> extends Comparable<ITree<P>>{

    Integer getId();


    String getLabel();

    Integer getOrderNum();

    List<ITree<P>> getChildren();

    P getPayload();

}
