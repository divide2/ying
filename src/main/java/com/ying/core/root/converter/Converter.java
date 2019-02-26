package com.ying.core.root.converter;

import lombok.Data;

import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;

/**
 * @author bvvy
 * @date 2018/12/28
 */
@Data

public class Converter<T> {

    private List<T> source;

    public  Converter(List<T> source) {
        this.source = source;
    }

    public static <T> Converter<T> of(List<T> t) {
        return new Converter<>(t);
    }

    public <R> List<R> convert(Function<T, R> function) {
        return source.stream().map(function).collect(Collectors.toList());
    }

}
