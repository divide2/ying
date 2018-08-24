package com.ying.auth.acl.model;

import com.ying.core.exception.SysException;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Type;
import org.hibernate.type.BasicType;
import org.hibernate.type.BasicTypeRegistry;

import javax.persistence.*;

/**
 * @author bvvy
 */
@Entity
@Table(name = "sys_acl")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Acl {


    private static final int MAX_INDEX = 31;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Column(name = "principal_id")
    private Integer principalId;


    @Column(name = "res_id")
    private Integer resId;

    @Column(name = "principal_type")
    private String principalType;

    @Column(name = "res_type")
    private String resType;

    @Column(name = "acl_status")
    private Integer aclStatus;

    /**
     * 设置权限，在某个位置设置访问或者无法访问
     * 0-31之间
     *
     * @param index  index
     * @param permit permit
     */
    public void setPermission(int index, boolean permit) {
        if (index < 0 || index > MAX_INDEX) {
            throw new SysException("invaid_index");
        }
        this.aclStatus = this.setBit(this.aclStatus, index, permit);
    }

    /**
     * 具体进行设置
     *
     * @param state  state
     * @param index  index
     * @param permit permit
     */
    private int setBit(int state, int index, boolean permit) {
        int tmp = 1;
        tmp = tmp << index;
        if (permit) {
            state = state | tmp;
        } else {
            tmp = ~tmp;
            state = state & tmp;
        }
        return state;
    }

    /**
     * 检查在某个位置是否可以访问
     *
     * @param index index
     * @return 是否有权限
     */
    public boolean checkPermission(int index) {
        int tmp = 1;
        tmp = tmp << index;
        int num = this.aclStatus & tmp;
        return num > 0;
    }

    /**
     * @param index     index
     * @param aclStatus aclstate
     * @return 是否有权限
     */
    public static boolean checkPermission(int index, int aclStatus) {
        int tmp = 1;
        tmp = tmp << index;
        int num = aclStatus & tmp;
        return num > 0;
    }

}
