package com.divide2.basis.service.impl;

import com.divide2.core.basic.service.impl.SimpleBasicServiceImpl;
import com.divide2.basis.model.GeneralCountry;
import com.divide2.basis.repo.CountryRepository;
import com.divide2.basis.service.CountryService;
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
