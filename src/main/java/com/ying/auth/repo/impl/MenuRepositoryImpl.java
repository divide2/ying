package com.ying.auth.repo.impl;

import com.querydsl.core.types.Projections;
import com.querydsl.jpa.impl.JPAQuery;
import com.ying.auth.bo.MenuBO;
import com.ying.auth.model.*;
import com.ying.auth.repo.custom.MenuRepositoryCustom;
import com.ying.auth.val.MenuType;
import com.ying.auth.vo.AclBO;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author bvvy
 */
@Repository
public class MenuRepositoryImpl implements MenuRepositoryCustom {


    private final EntityManager entityManager;

    public MenuRepositoryImpl(EntityManager entityManager) {
        this.entityManager = entityManager;
    }



}
