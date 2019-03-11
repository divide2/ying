package com.ying.team.vo;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;

/**
 * @author bvvy
 * @date 2019/3/10
 */
@Data
public class WorkbenchMenuVO {

    public WorkbenchMenuVO() {
    }

    private String id;
    private String icon;
    private String name;
    private String color;
    private boolean exist;

    private List<WorkbenchMenuVO> children;

    public WorkbenchMenuVO(String id, String icon, String name,String color) {
        this.id = id;
        this.icon = icon;
        this.name = name;
        this.color = color;
        children = new ArrayList<>();
    }

    public WorkbenchMenuVO(String id, String icon, String name, String color,List<WorkbenchMenuVO> children) {
        this.id = id;
        this.icon = icon;
        this.name = name;
        this.color = color;
        this.children = children;
    }
}
