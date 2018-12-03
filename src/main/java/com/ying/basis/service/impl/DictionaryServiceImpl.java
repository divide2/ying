package com.ying.basis.service.impl;

import com.ying.core.basic.service.impl.SimpleBasicServiceImpl;
import com.ying.basis.model.Dictionary;
import com.ying.basis.repo.DictionaryRepository;
import com.ying.basis.service.DictionaryService;
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
