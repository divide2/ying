package com.mj.general.port.repo;

import com.mj.general.port.model.Port;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author zejun
 * @date 2018/7/9 17:51
 */
@Repository
public interface PortRepository extends JpaRepository<Port,Integer>, QuerydslPredicateExecutor<Port> {

    /**
     * 根据港口代码查询数据
     * @param portCode 港口代码
     * @return Port
     */
    Port getByPortCode(String portCode);

    /**
     * 通过ids获取ports
     * @param ids ids
     * @return ports
     */
    List<Port> findByIdIn(List<Integer> ids);

}
