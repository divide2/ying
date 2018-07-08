package com.mj.auth.acl.repo.impl;

import com.mj.auth.acl.model.Acl;
import com.mj.auth.acl.model.QAcl;
import com.mj.auth.acl.repo.custom.AclRepositoryCustom;
import com.mj.auth.acl.vo.AclOperBO;
import com.mj.auth.principal.model.Role;
import com.mj.auth.res.model.QOper;
import com.querydsl.jpa.impl.JPAQuery;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import java.util.List;
import java.util.stream.Collectors;

import static com.querydsl.core.types.Projections.constructor;

/**
 * @author bvvy
 */
@Repository
public class AclRepositoryImpl implements AclRepositoryCustom {
    @Autowired
    private EntityManager entityManager;

    @Override
    public List<Integer> findAllResAndOperByRole(Integer roleId) {
        JPAQuery<Integer> query = new JPAQuery<>(entityManager);
        QAcl acl = QAcl.acl;
        QOper oper = QOper.oper;
        List<AclOperBO> aclOpers = query.select(constructor(AclOperBO.class, acl.aclStatus, oper.id, oper.indexPos))
                .from(acl).innerJoin(oper).on(oper.resId.eq(acl.resId))
                .where(acl.principalId.eq(roleId).and(acl.principalType.eq(Role.PRINCIPAL))).fetch();
        return aclOpers.stream().filter(it -> Acl.checkPermission(it.getOperIndexPos(), it.getAclStatus()))
                .map(AclOperBO::getOperId).collect(Collectors.toList());
    }


    @Override
    public List<Integer> findAllResAndOperByUser(Integer userId) {
        return null;
    }

    @Override
    public List<String> findAllOperCodeByUser(Integer userId) {
        return null;
    }

}
