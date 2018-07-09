package com.mj.general.port.repo;

import com.mj.general.port.model.Port;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;
import org.springframework.stereotype.Repository;

/**
 * @auther: zejun
 * @date: 2018/7/9 17:51
 */
@Repository
public interface PortRepository extends JpaRepository<Port,Integer>, QuerydslPredicateExecutor<Port> {
}
