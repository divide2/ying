package com.ying.product.model;

import com.vladmihalcea.hibernate.type.array.StringArrayType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Type;
import org.hibernate.annotations.TypeDef;

import javax.persistence.*;
import java.time.LocalDateTime;

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
@TypeDef(
        name = "string-array",
        typeClass = StringArrayType.class
)
public class Product  {

    @Id
    @GeneratedValue(generator = "custom-uuid")
    @GenericGenerator(
            name = "custom-uuid",
            strategy = "org.hibernate.id.UUIDGenerator",
            parameters = {
                    @org.hibernate.annotations.Parameter(
                            name = "uuid_gen_strategy_class",
                            value = "org.hibernate.id.uuid.CustomVersionOneStrategy"
                    )
            }
    )
    private String id;

    /**
     * 创建人
     */
    private Integer fromId;


    private String categoryId;

    private String teamId;


    /**
     * 名称
     */
    private String name;

    /**
     * 图片
     */

    @Type( type = "string-array" )
    @Column(
            name = "image",
            columnDefinition = "text[]"
    )
    private String [] image;

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
