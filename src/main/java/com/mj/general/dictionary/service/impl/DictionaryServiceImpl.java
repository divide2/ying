package com.mj.general.dictionary.service.impl;

import com.mj.core.basic.service.impl.SimpleBasicServiceImpl;
import com.mj.general.dictionary.model.Dictionary;
import com.mj.general.dictionary.repo.DictionaryRepository;
import com.mj.general.dictionary.service.DictionaryService;
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
}
