package com.ying.auth.acl.repo.impl;

import com.ying.auth.acl.model.Acl;
import com.ying.auth.acl.model.QAcl;
import com.ying.auth.acl.repo.custom.AclRepositoryCustom;
import com.ying.auth.acl.vo.AclOperBO;
import com.ying.auth.principal.model.Role;
import com.ying.auth.res.model.QOper;
import com.querydsl.jpa.impl.JPAQuery;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;
import java.util.stream.Collectors;

import static com.querydsl.core.types.Projections.constructor;

/**
 * @author bvvy
 */
public class AclRepositoryImpl implements AclRepositoryCustom {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public List<Integer> findAllResAndOperByRole(Integer roleId) {
        JPAQuery<AclOperBO> query = new JPAQuery<>(entityManager);
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

    @Override
    public List<Integer> findMenuIdsByRole(Integer roleId) {
        QAcl acl = QAcl.acl;
        JPAQuery<Acl> query = new JPAQuery<>(entityManager);
        List<Acl> acls = query.select(acl).from(acl)
                .where(acl.principalId.eq(roleId).and(acl.principalType.eq(Role.PRINCIPAL))).fetch();
        return acls.stream().map(Acl::getResId).collect(Collectors.toList());
    }

    @Override
    public Acl findResByPrincipal(Integer pid, String ptype, Integer rid, String rtype) {
        QAcl acl = QAcl.acl;
        JPAQuery<Acl> query = new JPAQuery<>(entityManager);
        return query.select(acl).from(acl).where(
                acl.principalId.eq(pid).and(acl.principalType.eq(ptype))
                        .and(acl.resId.eq(rid)).and(acl.resType.eq(rtype))
        ).fetchOne();
    }
}
