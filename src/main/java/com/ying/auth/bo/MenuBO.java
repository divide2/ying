package com.ying.auth.bo;

import com.ying.auth.payload.MenuPayload;
import com.ying.auth.payload.MenuTreeMerger;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author bvvy
 * @date 2018/9/18
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class MenuBO {
    private Integer id;
    private String label;
    private Integer pid;
    private String path;
    private Integer orderNum;
    private String icon;

    public MenuTreeMerger to() {
        return new MenuTreeMerger(this.getId(), this.getLabel(), this.getPid(), this.getOrderNum(), new MenuPayload(this.getPath(), this.getIcon()));
    }
}
