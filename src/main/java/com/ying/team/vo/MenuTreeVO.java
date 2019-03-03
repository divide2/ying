package com.ying.team.vo;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;

/**
 * @author bvvy
 * @date 2019/3/3
 */
@Data
public class MenuTreeVO {

    private String id;
    private String icon;
    private String name;

    public MenuTreeVO() {


    }
    public MenuTreeVO(String id, String icon, String name, List<MenuTreeVO> children) {
        this.id = id;
        this.icon = icon;
        this.name = name;
        this.children = children;
    }


    public MenuTreeVO(String id, String icon, String name) {
        this.id = id;
        this.icon = icon;
        this.name = name;
        this.children = new ArrayList<>();
    }

    private List<MenuTreeVO> children;

}
