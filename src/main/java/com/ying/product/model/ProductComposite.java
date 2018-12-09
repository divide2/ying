package com.ying.product.model;

/**
 * 产品组成 还是由别人的产品合成的
 * @author bvvy
 * @date 2018/12/9
 */

public class ProductComposite {

    private Integer id;
    private Integer productId;

    /**
     * 组成的id 还是product
     */
    private Integer compositeId;
    private Integer amount;

}
