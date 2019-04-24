package com.divide2.order.model;

import com.divide2.core.model.BaseEntity;
import com.divide2.product.unit.UnitAmount;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Type;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

/**
 * 订单最后一层 是规格级别的
 * @author bvvy
 * @date 2018/12/2
 */
@EqualsAndHashCode(callSuper = false)
@Data
@Entity
@Table(name="o_order_product_spec")
public class OrderProductSpec extends BaseEntity {

    private String orderId;
    private String orderProductId;
    private String productName;
    private String productId;
    private String productSpecId;
    private String specName;
    private BigDecimal price;

    @Type(type = "jsonb")
    @Column(columnDefinition = "jsonb")
    private List<UnitAmount> unitAmounts;
    private LocalDateTime createTime;
}
