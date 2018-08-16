package com.ying.general.dictionary.service.impl;

import com.ying.core.basic.service.impl.SimpleBasicServiceImpl;
import com.ying.general.dictionary.model.Dictionary;
import com.ying.general.dictionary.repo.DictionaryRepository;
import com.ying.general.dictionary.service.DictionaryService;
import org.springframework.stereotype.Service;

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
}
