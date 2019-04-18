package com.divide2.order.query;

import com.divide2.core.root.query.QueryField;
import com.divide2.core.root.query.QueryParam;
import com.divide2.order.model.Order;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

/**
 * @author bvvy
 * @date 2018/12/2
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class OrderQueryParam implements QueryParam {

    @QueryField(entity = Order.class)
    private LocalDate createTime;

    @QueryField(entity = Order.class)
    private String status;

}
