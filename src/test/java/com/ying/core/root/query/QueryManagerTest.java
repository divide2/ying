package com.ying.core.root.query;

import com.querydsl.core.types.Predicate;
import com.ying.order.query.OrderQueryParam;
import org.junit.Test;
import org.springframework.util.ReflectionUtils;

import java.time.LocalDate;

/**
 * @author bvvy
 * @date 2018/12/23
 */
public class QueryManagerTest {

    @Test
    public void testClassFiled() {
        QueryParam orderQueryParam = new OrderQueryParam(LocalDate.now(), "");
        ReflectionUtils.doWithFields(orderQueryParam.getClass(), field -> {
            System.out.println(field.getName());
            field.setAccessible(true);
            System.out.println(field.get(orderQueryParam));
        });
    }

    @Test
    public void testPredicate() {
        QueryParam orderQueryParam = new OrderQueryParam(LocalDate.now(), "z");
        Predicate predicate = QueryManager.resolvePredicate(orderQueryParam);
        System.out.println(predicate);

    }

}
