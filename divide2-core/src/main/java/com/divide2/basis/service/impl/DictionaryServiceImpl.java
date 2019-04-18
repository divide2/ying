package com.divide2.basis.service.impl;

import com.querydsl.core.types.dsl.BooleanExpression;
import com.divide2.basis.model.QDictionary;
import com.divide2.core.basic.service.impl.SimpleBasicServiceImpl;
import com.divide2.basis.model.Dictionary;
import com.divide2.basis.repo.DictionaryRepository;
import com.divide2.basis.service.DictionaryService;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author zejun
 * @date 2018/7/30 09:20
 */
@Service
public class DictionaryServiceImpl extends SimpleBasicServiceImpl<Dictionary,Integer,DictionaryRepository>
        implements DictionaryService {
    private final DictionaryRepository dictionaryRepository;

    public DictionaryServiceImpl(DictionaryRepository dictionaryRepository) {
        this.dictionaryRepository = dictionaryRepository;
    }

    @Override
    public Dictionary getByGroupCodeAndCode(String groupCode, String code) {
        return dictionaryRepository.getByGroupCodeAndCode(groupCode, code);
    }

    @Override
    public List<Dictionary> findByGroup(String groupCode) {
        QDictionary dictionary = QDictionary.dictionary;
        BooleanExpression predicate = dictionary.groupCode.eq(groupCode);
        return (List<Dictionary>)dictionaryRepository.findAll(predicate);
    }
}
