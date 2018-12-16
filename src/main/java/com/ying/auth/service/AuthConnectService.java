package com.ying.auth.service;

import com.ying.basis.model.Company;
import com.ying.core.basic.service.ConnectService;

/**
 * @author bvvy
 * @date 2018/12/16
 */
public interface AuthConnectService extends ConnectService {
    Company getCompany(Integer companyId);

}
