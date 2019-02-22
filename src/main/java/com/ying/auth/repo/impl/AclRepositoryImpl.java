package com.ying.auth.repo.impl;

import com.querydsl.jpa.impl.JPAQuery;
import com.ying.auth.repo.custom.AclRepositoryCustom;
import com.ying.team.model.QAcl;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

/**
 * @author bvvy
 */
public class AclRepositoryImpl implements AclRepositoryCustom {

    @PersistenceContext
    private EntityManager entityManager;
    private QAcl acl = QAcl.acl;


    @Override
    public List<String> findAuthorities(Integer roleId) {

        JPAQuery<?> query = new JPAQuery<>(entityManager);
        return query.select(acl.resCode).from(acl)
                        .where(acl.roleId.eq(roleId)).fetch();
    }
}
