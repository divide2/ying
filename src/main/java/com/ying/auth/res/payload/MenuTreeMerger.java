package com.ying.auth.res.payload;

import com.ying.core.data.tree.ITreeMerger;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author bvvy
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class MenuTreeMerger implements ITreeMerger<MenuPayload> {
    private Integer id;
    private String label;
    private Integer pid;
    private Integer orderNum;
    private MenuPayload payload;
}

