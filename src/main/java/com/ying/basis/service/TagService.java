package com.ying.basis.service;

import com.ying.basis.model.Tag;
import com.ying.basis.vo.TagVO;
import com.ying.core.basic.service.BasicService;

import java.util.List;

/**
 * @author bvvy
 * @date 2018/7/19
 * todo 把tag 用于不同的类型上面，目前tag只用于作品部分
 */
public interface TagService extends BasicService<Tag, Integer> {
    /**
     * 通过名称列表添加
     * @param tagNames 名称列表
     */
    void add(List<String> tagNames);

    /**
     * 获取一定数量的tag
     *
     * @param count 数量
     * @return tag
     */
    List<TagVO> findByCount(Integer count);
}
