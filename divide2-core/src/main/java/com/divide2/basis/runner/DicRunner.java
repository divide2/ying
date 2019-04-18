package com.divide2.basis.runner;

import com.divide2.basis.model.Dictionary;
import com.divide2.basis.repo.DictionaryRepository;
import com.divide2.core.data.properties.DivideProperties;
import com.divide2.core.val.Punctuation;
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

    private final DivideProperties divideProperties;
    private final DictionaryRepository dictionaryRepository;

    public DicRunner(
            DivideProperties divideProperties,
            DictionaryRepository dictionaryRepository) {
        this.divideProperties = divideProperties;
        this.dictionaryRepository = dictionaryRepository;
    }

    /**
     * 目前只能用于一个短线的
     * abc-abc -> abcAbc
     *
     * @param word word
     * @return word
     */
    private static String minus2Upper(String word) {
        int index = word.indexOf(Punctuation.MINUS);
        if (index > 0) {
            return word.substring(0, index) +
                    word.substring(index + 1, index + 2).toUpperCase() +
                    word.substring(index + 2);
        }
        return word;
    }

    @Override
    public void run(String... args) throws Exception {

        Map<String, Map<String, Map<String, String>>> dics = divideProperties.getDic();
        dics.forEach((pk, pv) -> pv.forEach((gk, gv) -> gv.forEach((k, v) -> {
            log.info("init dictionary into db property-> {} group->{}  code-> {}  value->{}", pk, gk, k, v);
            String groupCode = pk + Punctuation.DOT + gk;
            String code = minus2Upper(k);
            Dictionary dictionary = dictionaryRepository.getByGroupCodeAndCode(groupCode, code);
            if (dictionary == null) {
                dictionary = new Dictionary();
            }
            dictionary.setGroupCode(groupCode);
            dictionary.setCode(code);
            dictionary.setValue(v);
            dictionaryRepository.save(dictionary);
        })));
        log.info("init dictionary completed");
    }

}
