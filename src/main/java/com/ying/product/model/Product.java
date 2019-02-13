package com.ying.product.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Type;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.function.Function;

/**
 * @author bvvy
 * @date 2018/8/16
 */
@Data
@Entity
@Table(name = "p_product")
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    /**
     * 创建人
     */
    private Integer fromId;

    /**
     * 公司id
     */
    private Integer companyId;

    /**
     * 名称
     */
    private String name;

    /**
     * 图片
     */
    private String image;

    /**
     * 单位
     */
    private String unit;

    /**
     * 创建人
     */
    private String fromName;


    /**
     * 创建日期
     */
    private LocalDateTime createTime;

    /**
     * 修改时间
     */
    private LocalDateTime updateTime;

    /**
     * 启用
     */
    @Type(type = "yes_no")
    private Boolean enabled;
    /**
     * 备注 描述
     */
    private String remarks;

}
