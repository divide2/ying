package com.ying.doc.tag.service;

import com.ying.core.basic.service.BasicService;
import com.ying.doc.tag.model.Tag;
import com.ying.doc.tag.vo.TagVO;

import java.util.List;

/**
 * @author bvvy
 * @date 2018/7/19
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
