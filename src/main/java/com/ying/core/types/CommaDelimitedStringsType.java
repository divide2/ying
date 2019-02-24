package com.ying.core.types;

import com.vladmihalcea.hibernate.type.array.IntArrayType;
import org.hibernate.type.AbstractSingleColumnStandardBasicType;
import org.hibernate.type.descriptor.sql.VarcharTypeDescriptor;
import org.hibernate.usertype.DynamicParameterizedType;

import java.util.List;
import java.util.Properties;

/**
 * @author bvvy
 * @date 2019/2/24
 */
public class CommaDelimitedStringsType extends AbstractSingleColumnStandardBasicType<List> implements DynamicParameterizedType {

    public CommaDelimitedStringsType() {
        super(
                VarcharTypeDescriptor.INSTANCE,
                new CommaDelimitedStringsJavaTypeDescriptor()
        );
    }

    @Override
    public String getName() {
        return "comma_delimited_strings";
    }

    @Override
    public void setParameterValues(Properties parameters) {
        ((CommaDelimitedStringsJavaTypeDescriptor) getJavaTypeDescriptor()).setParameterValues(parameters);
    }

    @Override
    protected boolean registerUnderJavaType() {
        return true;
    }
}
