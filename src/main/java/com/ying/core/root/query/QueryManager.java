package com.ying.core.root.query;

import com.querydsl.core.types.EntityPath;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.core.types.dsl.Expressions;
import org.apache.commons.lang3.StringUtils;
import org.springframework.data.querydsl.EntityPathResolver;
import org.springframework.data.querydsl.SimpleEntityPathResolver;
import org.springframework.util.ReflectionUtils;

import java.lang.reflect.Field;

/**
 * @author bvvy
 * @date 2018/12/23
 */
public class QueryManager {

    private static final EntityPathResolver entityPathResolver = SimpleEntityPathResolver.INSTANCE;


    public static BooleanExpression resolvePredicate(QueryParam queryParam) {
        BooleanExpression predicate = Expressions.ONE.eq(Expressions.ONE);
        X x = new X(predicate, queryParam);
        ReflectionUtils.doWithFields(queryParam.getClass(), x, field -> {
            try {
                field.setAccessible(true);
                Object o = field.get(queryParam);
                if (o == null) {
                    return false;
                }
                if (o instanceof CharSequence && StringUtils.isBlank((CharSequence) o)) {
                    return false;
                }
            } catch (IllegalAccessException e) {
                throw new RuntimeException(e);
            }
            return true;
        });
        return x.getPredicate();
    }

    static class X implements ReflectionUtils.FieldCallback {
        private BooleanExpression predicate;
        private QueryParam queryParam;

        public X(BooleanExpression predicate, QueryParam queryParam) {
            this.predicate = predicate;
            this.queryParam = queryParam;
        }

        public BooleanExpression getPredicate() {
            return predicate;
        }

        @Override
        public void doWith(Field field) throws IllegalArgumentException, IllegalAccessException {
            field.setAccessible(true);
            QueryField queryField = field.getAnnotation(QueryField.class);
            EntityPath<?> path = entityPathResolver.createPath(queryField.entity());
            String columnName = StringUtils.defaultIfBlank(queryField.value(), field.getName());
            this.predicate = predicate.and(Expressions.predicate(
                    queryField.op(),
                    Expressions.path(String.class, path, columnName),
                    Expressions.constant(field.get(queryParam))
            ));
        }

    }
}
