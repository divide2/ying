package com.divide2.product.model;

import com.vladmihalcea.hibernate.type.array.StringArrayType;
import lombok.Data;
import org.codehaus.jackson.annotate.JsonIgnore;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Type;
import org.hibernate.annotations.TypeDef;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;

/**
 * 产品的规格价格 不同规格价格可能不同
 * @author bvvy
 * @date 2018/12/3
 */
@Entity
@Table(name = "p_product_spec")
@Data
@TypeDef(
        name = "string-array",
        typeClass = StringArrayType.class
)
public class ProductSpec {

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
    @NotEmpty
    private String name;

    @JsonIgnore
    private String productId;

    @NotNull
    private BigDecimal price;

    @Type( type = "string-array" )
    @Column(
            name = "image",
            columnDefinition = "text[]"
    )
    private String [] image;
}
