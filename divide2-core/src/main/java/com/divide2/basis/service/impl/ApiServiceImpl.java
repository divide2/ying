package com.divide2.basis.service.impl;

import com.divide2.basis.model.Api;
import com.divide2.basis.repo.ApiRepository;
import com.divide2.basis.service.ApiService;
import com.divide2.core.basic.service.impl.SimpleBasicServiceImpl;
import org.springframework.stereotype.Service;

/**
 * @author bvvy
 */
@Service
public class ApiServiceImpl extends SimpleBasicServiceImpl<Api,Integer,ApiRepository>implements ApiService {

    private final ApiRepository apiRepository;

    public ApiServiceImpl(ApiRepository apiRepository) {
        this.apiRepository = apiRepository;
    }

    @Override
    public Api add(Api api) {
        Api tApi = apiRepository.findByPathAndMethod(api.getPath(),api.getMethod());
        if(tApi == null) {
            tApi = new Api();
        }
        tApi.setPath(api.getPath());
        tApi.setMethod(api.getMethod());
        tApi.setName(api.getName());
        return apiRepository.save(tApi);
    }
}
