package com.ying.auth.res.repo;

import com.ying.auth.res.model.Api;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @author bvvy
 */
public interface ApiRepository extends JpaRepository<Api,Integer> {
    /**
     * 通过路径获取
     * @param path 路径
     * @param method 方法 post get ...
     * @return oper
     */
    Api findByPathAndMethod(String path, String method);
}
