package com.ying.basis.vo;

import com.ying.basis.model.Tag;
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

    public static TagVO of(Tag tag) {
        return new TagVO(tag.getName());
    }
}
