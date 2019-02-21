package com.ying.friend.model;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * 聊天群
 * @author bvvy
 * @date 2019/2/21
 */
@Entity
@Table(name = "f_chat_group")
@Data
public class ChatGroup {

    private String id;
    private String name;
    private String avatar;

}
