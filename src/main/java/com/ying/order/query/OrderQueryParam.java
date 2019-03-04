package com.ying.order.query;

import com.ying.core.root.query.QueryField;
import com.ying.core.root.query.QueryParam;
import com.ying.order.model.Order;
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
