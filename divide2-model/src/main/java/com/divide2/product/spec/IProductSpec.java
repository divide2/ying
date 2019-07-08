package com.divide2.product.spec;

import java.math.BigDecimal;

/**
 * @author bvvy
 * @date 2019/4/18
 */
public interface IProductSpec {

    String getId();

    String getName();

    BigDecimal getPrice();

    String[] getImage();

}
