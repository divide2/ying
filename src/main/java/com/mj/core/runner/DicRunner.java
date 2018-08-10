package com.mj.core.runner;

import com.mj.core.data.properties.DicProperties;
import com.mj.general.dictionary.model.Dictionary;
import com.mj.general.dictionary.service.DictionaryService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.Map;

/**
 * @author bvvy
 * @date 2018/8/10
 */

@Slf4j
@Component
public class DicRunner implements CommandLineRunner {

    private final DicProperties dicProperties;
    private final DictionaryService dictionaryService;

    public DicRunner(
            DicProperties dicProperties,
            DictionaryService dictionaryService) {
        this.dicProperties = dicProperties;
        this.dictionaryService = dictionaryService;
    }


    @Override
    public void run(String... args) throws Exception {

        Map<String, Map<String, String>> dics = dicProperties.getDic();
        dics.forEach((gk, gv) -> {
            gv.forEach((k, v) -> {
                log.info("init dictionary into db group->{}  code-> {}  value->{}", gk, k, v);
                Dictionary dictionary = Dictionary.builder()
                        .groupCode(gk)
                        .code(k)
                        .value(v)
                        .build();
                dictionaryService.add(dictionary);
            });
        });
        log.info("init dictionary completed");
    }
}
