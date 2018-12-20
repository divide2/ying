package com.ying.order.query;

import com.ying.core.root.query.QueryParam;
import lombok.Data;

import java.time.LocalDate;

/**
 * @author bvvy
 * @date 2018/12/2
 */
@Data
public class OrderQuery implements QueryParam {


    private LocalDate createTime;
    private String status;

}
