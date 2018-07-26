package com.mj.general.container.repo;

import com.mj.general.container.model.Container;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;
import org.springframework.stereotype.Repository;

/**
 * @author zejun
 * @date 2018/7/26 15:44
 */
@Repository
public interface ContainerRepository extends JpaRepository<Container,Integer>, QuerydslPredicateExecutor<Container> {

    /**
     *  根据code查数据，忽视大小写
     * @param containerCode code
     * @return Container
     */
    Container getByContainerCodeIgnoreCase(String containerCode);
}
