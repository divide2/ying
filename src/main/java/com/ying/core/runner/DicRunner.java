package com.ying.core.runner;

import com.ying.core.data.properties.DicProperties;
import com.ying.core.val.Punctuation;
import com.ying.general.dictionary.model.Dictionary;
import com.ying.general.dictionary.repo.DictionaryRepository;
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
    private final DictionaryRepository dictionaryRepository;

    public DicRunner(
            DicProperties dicProperties,
            DictionaryRepository dictionaryRepository) {
        this.dicProperties = dicProperties;
        this.dictionaryRepository = dictionaryRepository;
    }


    @Override
    public void run(String... args) throws Exception {

        Map<String, Map<String, String>> dics = dicProperties.getDic();
        dics.forEach((gk, gv) -> {
            gv.forEach((k, v) -> {
                log.info("init dictionary into db group->{}  code-> {}  value->{}", gk, k, v);
                String groupCode = minus2Upper(gk);
                Dictionary dictionary = dictionaryRepository.getByGroupCodeAndCode(groupCode, k);
                if (dictionary == null) {
                    dictionary = new Dictionary();
                }
                dictionary.setGroupCode(groupCode);
                dictionary.setCode(k);
                dictionary.setValue(v);
                dictionaryRepository.save(dictionary);
            });
        });
        log.info("init dictionary completed");
    }

    /**
     * 目前只能用于一个短线的
     * abc-abc -> abcAbc
     * @param word word
     * @return word
     */
    private static String minus2Upper(String word) {
        int index = word.indexOf(Punctuation.MINUS);
        if (index > 0) {
            return word.substring(0, index) +
                    word.substring(index+1, index + 2).toUpperCase() +
                    word.substring(index + 2);
        }
        return word;
    }

}
