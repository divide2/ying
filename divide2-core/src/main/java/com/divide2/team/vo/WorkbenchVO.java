package com.divide2.team.vo;

import lombok.Data;

import java.util.List;

/**
 * @author bvvy
 * @date 2019/2/26
 */
@Data
public class WorkbenchVO {

    private MenuGroupVO menuGroup;
    private List<MenuVO> menus;
}
