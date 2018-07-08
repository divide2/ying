package com.mj.auth.res.service.impl;

import com.mj.auth.res.model.Api;
import com.mj.auth.res.repo.ApiRepository;
import com.mj.auth.res.service.ApiService;
import com.mj.core.service.impl.SimpleBasicServiceImpl;
import org.springframework.stereotype.Service;

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
