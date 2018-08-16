package com.ying.auth.res.service.impl;

import com.ying.auth.res.model.Api;
import com.ying.auth.res.repo.ApiRepository;
import com.ying.auth.res.service.ApiService;
import com.ying.core.basic.service.impl.SimpleBasicServiceImpl;
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
