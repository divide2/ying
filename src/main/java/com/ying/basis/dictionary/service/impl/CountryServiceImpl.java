package com.ying.basis.dictionary.service.impl;

import com.ying.core.basic.service.impl.SimpleBasicServiceImpl;
import com.ying.basis.dictionary.model.GeneralCountry;
import com.ying.basis.dictionary.repo.CountryRepository;
import com.ying.basis.dictionary.service.CountryService;
import org.springframework.stereotype.Service;

/**
 * @author zejun
 * @date 2018/7/27 18:21
 */
@Service
public class CountryServiceImpl extends SimpleBasicServiceImpl<GeneralCountry,Integer,CountryRepository> implements CountryService {
    private final CountryRepository countryRepository;

    public CountryServiceImpl(CountryRepository countryRepository) {
        this.countryRepository = countryRepository;
    }
}
