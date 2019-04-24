package com.divide2.product.unit;

import java.util.List;

/**
 * @author bvvy
 * @date 2019/4/24
 */
public class Uniter {

    public static StockUnit compose(List<UnitAmount> unitAmounts) {
        int totalAmount = 0;
        StringBuilder units = new StringBuilder();
        for (UnitAmount unitAmount : unitAmounts) {
            totalAmount += unitAmount.getAmount();
            units.append(totalAmount).append(unitAmount.getName());
        }
        return new StockUnit(units.toString(), totalAmount);
    }
}
