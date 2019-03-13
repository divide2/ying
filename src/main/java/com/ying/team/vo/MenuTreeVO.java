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
    private String color;
    private boolean leaf;

    private List<MenuTreeVO> children;
    public MenuTreeVO() {


    }

    public MenuTreeVO(String id, String icon, String name,String color,boolean leaf, List<MenuTreeVO> children) {
        this.id = id;
        this.icon = icon;
        this.name = name;
        this.color = color;
        this.leaf = leaf;
        this.children = children;
    }


    public MenuTreeVO(String id, String icon, String name,String color,boolean leaf) {
        this.leaf = leaf;
        this.id = id;
        this.icon = icon;
        this.name = name;
        this.color = color;
        this.children = new ArrayList<>();
    }

}
