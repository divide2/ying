package com.ying.core.data.tree;

import com.ying.core.data.tree.payload.Payload;

/**
 * @author bvvy
 * @date 2018/9/18
 */
public interface ITreeMerger<P extends Payload> {
    Integer getId();

    Integer getPid();

    String getLabel();

    Integer getOrderNum();

    P getPayload();

}
