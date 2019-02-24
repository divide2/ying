package com.ying.product.model;

import com.vladmihalcea.hibernate.type.array.IntArrayType;
import com.vladmihalcea.hibernate.type.array.StringArrayType;
import com.ying.core.model.BaseEntity;
import lombok.Data;
import org.codehaus.jackson.annotate.JsonIgnore;
import org.hibernate.annotations.Type;
import org.hibernate.annotations.TypeDef;
import org.hibernate.annotations.TypeDefs;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.util.List;

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
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @NotEmpty
    private String name;

    @JsonIgnore
    private Integer productId;

    @NotNull
    private BigDecimal price;

    @Type( type = "string-array" )
    @Column(
            name = "image",
            columnDefinition = "text[]"
    )
    private String [] image;
}
