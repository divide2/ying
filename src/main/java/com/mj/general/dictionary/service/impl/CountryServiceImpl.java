package com.mj.general.dictionary.service.impl;

import com.mj.core.service.impl.SimpleBasicServiceImpl;
import com.mj.general.dictionary.model.GeneralCountry;
import com.mj.general.dictionary.repo.CountryRepository;
import com.mj.general.dictionary.service.CountryService;
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
