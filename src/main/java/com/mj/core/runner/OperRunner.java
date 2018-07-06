package com.mj.core.runner;

import com.mj.auth.res.model.Oper;
import com.mj.auth.res.service.OperService;
import com.mj.core.val.Punctuation;
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
public class OperRunner implements CommandLineRunner {

    private final RequestMappingHandlerMapping requestMappingHandlerMapping;
    private final OperService operService;

    @Autowired
    public OperRunner(RequestMappingHandlerMapping requestMappingHandlerMapping,
                      OperService operService) {
        this.requestMappingHandlerMapping = requestMappingHandlerMapping;
        this.operService = operService;
    }

    @Override
    public void run(String... args) throws Exception {
        Map<RequestMappingInfo, HandlerMethod> handlerMethods = requestMappingHandlerMapping.getHandlerMethods();
        log.info("start init oper to database....");
        handlerMethods.forEach((k, v) -> {
            Oper oper = new Oper();
            oper.setName(k.getName());
            Set<RequestMethod> methods = k.getMethodsCondition().getMethods();
            System.out.println(k.getPatternsCondition().getPatterns());
            Set<String> patterns = k.getPatternsCondition().getPatterns();
            oper.setPath(patterns.stream().collect(joining(Punctuation.VERTICAL)));
            if (methods.size() > 0) {
                oper.setMethod(methods.stream().map(Enum::toString).collect(joining(Punctuation.VERTICAL)));
            } else {
                oper.setMethod("ALL");
            }
            log.info("init oper path into database: {}", oper);
            operService.add(oper);
        });
        log.info("init oper path to db is completed");
    }
}
