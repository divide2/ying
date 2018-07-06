package com.mj.auth.res.repo;

import com.mj.auth.res.model.Oper;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @author bvvy
 */
public interface OperRepository extends JpaRepository<Oper,Integer> {

    /**
     * 通过路径获取
     * @param path 路径
     * @param method 方法 post get ...
     * @return oper
     */
    Oper findByPathAndMethod(String path,String method);
}
