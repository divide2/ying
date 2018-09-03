package com.ying.core.er;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import com.ying.core.exception.SysException;
import org.springframework.stereotype.Component;

import java.io.IOException;

/**
 * @author bvvy
 * @date 2018/8/21
 */
@Component
public class Jsoner {

    private static ObjectMapper objectMapper = new ObjectMapper();

    public static String toJson(Object obj) {
        try {
            return objectMapper.writeValueAsString(obj);
        } catch (JsonProcessingException e) {
            throw new SysException(e.getMessage());
        }
    }

    public static <T> T from(String json, Class<T> clz) {
        try {
            return objectMapper.readValue(json, clz);
        } catch (IOException e) {
            throw new SysException(e.getMessage());
        }
    }
    public static <T> T fromSnake(String json, Class<T> clz) {
        try {
            ObjectMapper objectMapper = new ObjectMapper();
            objectMapper.setPropertyNamingStrategy(PropertyNamingStrategy.SNAKE_CASE);
            return objectMapper.readValue(json, clz);
        } catch (IOException e) {
            throw new SysException(e.getMessage());
        }
    }
}
