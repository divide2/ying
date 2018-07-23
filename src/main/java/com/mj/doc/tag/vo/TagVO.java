package com.mj.doc.tag.vo;

import com.mj.doc.tag.model.Tag;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author bvvy
 * @date 2018/7/20
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class TagVO {


    private String name;

    public static TagVO fromTag(Tag tag) {
        return new TagVO(tag.getName());
    }
}
