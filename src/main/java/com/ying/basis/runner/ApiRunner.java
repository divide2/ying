package com.ying.basis.runner;

import com.ying.basis.model.Api;
import com.ying.basis.service.ApiService;
import com.ying.core.val.Punctuation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.mvc.method.RequestMappingInfo;
import org.springframework.web.servlet.mvc.method.annotation.RequestMappingHandlerMapping;

import java.util.Map;
import java.util.Set;

import static java.util.stream.Collectors.joining;

/**
 * @author bvvy
 * 初始化接口地址到数据库。
 */
@Component
@Slf4j
public class ApiRunner implements CommandLineRunner {

    private final RequestMappingHandlerMapping requestMappingHandlerMapping;
    private final ApiService apiService;

    @Autowired
    public ApiRunner(RequestMappingHandlerMapping requestMappingHandlerMapping,
                     ApiService apiService) {
        this.requestMappingHandlerMapping = requestMappingHandlerMapping;
        this.apiService = apiService;
    }

    @Override
    public void run(String... args) throws Exception {
        Map<RequestMappingInfo, HandlerMethod> handlerMethods = requestMappingHandlerMapping.getHandlerMethods();
        log.info("start init api to database....");
        handlerMethods.forEach((k, v) -> {
            Api api = new Api();
            api.setName(k.getName());
            Set<RequestMethod> methods = k.getMethodsCondition().getMethods();
            Set<String> patterns = k.getPatternsCondition().getPatterns();
            api.setPath(patterns.stream().collect(joining(Punctuation.VERTICAL)));
            if (methods.size() > 0) {
                api.setMethod(methods.stream().map(Enum::toString).collect(joining(Punctuation.VERTICAL)));
            } else {
                api.setMethod("ALL");
            }
            log.info("init api path into database: {}", api);
            apiService.add(api);
        });
        log.info("init api path to db is completed");
    }
}
