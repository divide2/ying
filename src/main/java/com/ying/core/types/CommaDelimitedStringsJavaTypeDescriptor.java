package com.ying.core.types;

import com.ying.core.val.Punctuation;
import org.hibernate.type.descriptor.WrapperOptions;
import org.hibernate.type.descriptor.java.AbstractTypeDescriptor;
import org.hibernate.type.descriptor.java.MutableMutabilityPlan;
import org.hibernate.usertype.DynamicParameterizedType;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Properties;
import java.util.stream.Collectors;

/**
 * @author bvvy
 * @date 2019/2/24
 */
public class CommaDelimitedStringsJavaTypeDescriptor extends AbstractTypeDescriptor<List> implements DynamicParameterizedType {

    public static final String DELIMITER = Punctuation.VERTICAL;

    @Override
    public void setParameterValues(Properties parameters) {

    }

    public CommaDelimitedStringsJavaTypeDescriptor() {
        super(
                List.class,
                new MutableMutabilityPlan<List>() {
                    @Override
                    protected List deepCopyNotNull(List value) {
                        return new ArrayList( value );
                    }
                }
        );
    }

    @Override
    public String toString(List value) {
        return ( (List<String>) value ).stream().collect( Collectors.joining( DELIMITER ) );
    }

    @Override
    public List fromString(String string) {
        List<String> values = new ArrayList<>();
        Collections.addAll( values, string.split( DELIMITER ) );
        return values;
    }

    @Override
    public <X> X unwrap(List value, Class<X> type, WrapperOptions options) {
        return (X) toString( value );
    }

    @Override
    public <X> List wrap(X value, WrapperOptions options) {
        return fromString( (String) value );
    }
}