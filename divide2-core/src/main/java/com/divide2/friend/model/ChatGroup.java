package com.divide2.friend.model;

import lombok.Data;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
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

    @Id
    @GeneratedValue(generator = "uuid")
    @GenericGenerator(name = "uuid", strategy = "uuid")
    private String id;

    private String name;

    private String avatar;

}
