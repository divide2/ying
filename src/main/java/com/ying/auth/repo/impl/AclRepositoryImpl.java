package com.ying.auth.repo.impl;

import com.ying.auth.repo.custom.AclRepositoryCustom;
import com.ying.team.model.QAcl;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 * @author bvvy
 */
public class AclRepositoryImpl implements AclRepositoryCustom {

    @PersistenceContext
    private EntityManager entityManager;
    private QAcl acl = QAcl.acl;

}
