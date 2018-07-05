package com.mj.auth.acl.repo.impl;

import com.mj.auth.acl.model.Acl;
import com.mj.auth.acl.repo.custom.AclRepositoryCustom;
import com.mj.auth.res.model.Menu;
import com.querydsl.jpa.impl.JPAQuery;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import java.util.List;

/**
 * @author bvvy
 */
@Repository
public class AclRepositoryImpl implements AclRepositoryCustom {
    @Autowired
    private EntityManager entityManager;


    @Override
    public List<Acl> findByRole(Integer roleId) {

        JPAQuery<Menu> query = new JPAQuery<>(entityManager);
        query.select().fetch();
        return null;
    }

    @Override
    public List<Acl> findByPrincipal(Integer principalId, Integer principalType) {
        return null;
    }
}
