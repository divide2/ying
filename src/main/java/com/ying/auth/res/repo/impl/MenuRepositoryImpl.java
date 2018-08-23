package com.ying.auth.res.repo.impl;

import com.ying.auth.acl.model.Acl;
import com.ying.auth.acl.model.QAcl;
import com.ying.auth.acl.vo.AclBO;
import com.ying.auth.principal.model.QUserRole;
import com.ying.auth.principal.model.Role;
import com.ying.auth.res.model.Menu;
import com.ying.auth.res.model.QMenu;
import com.ying.auth.res.repo.custom.MenuRepositoryCustom;
import com.ying.auth.res.val.MenuType;
import com.ying.core.data.tree.TreeMerger;
import com.querydsl.core.types.Projections;
import com.querydsl.jpa.impl.JPAQuery;
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

    private JPAQuery<TreeMerger> treeQuery() {
        return new JPAQuery<>(entityManager);
    }

    @Override
    public List<TreeMerger> findMenuTree() {
        return findMenuTree(null);
    }

    @Override
    public List<TreeMerger> findLeftMenuTree() {
        return findMenuTree(Menu.RES_TYPE);
    }

    private List<TreeMerger> findMenuTree(String menuType) {
        QMenu menu = QMenu.menu;
        JPAQuery<TreeMerger> query = new JPAQuery<>(entityManager);
        query.select(Projections.constructor(TreeMerger.class, menu.id, menu.name, menu.pid, menu.path, menu.orderNum))
                .from(menu);
        if (StringUtils.isNotEmpty(menuType)) {
            query.where(menu.type.eq(menuType));
        }
        return query.fetch();
    }

    @Override
    public List<TreeMerger> findLeftMenuTreeByUser(Integer userId) {
        QAcl acl = QAcl.acl;
        QMenu menu = QMenu.menu;
        QUserRole userRole = QUserRole.userRole;
        JPAQuery<AclBO> query = new JPAQuery<>(entityManager);
        List<AclBO> acls = query.select(Projections.constructor(AclBO.class, acl.aclStatus, menu.id, menu.name, menu.pid, menu.path, menu.orderNum))
                .from(acl).innerJoin(menu).on(menu.id.eq(acl.resId)).innerJoin(userRole).on(userRole.roleId.eq(acl.principalId))
                .where(acl.resType.eq(Menu.RES_TYPE).and(menu.type.eq(MenuType.NAV))
                        .and(userRole.userId.eq(userId)).and(acl.principalType.eq(Role.PRINCIPAL))).fetch();
        return acls.stream().filter(vo -> Acl.checkPermission(0, vo.getAclState())).map(
                vo -> new TreeMerger(vo.getResId(), vo.getResLabel(), vo.getResPid(), vo.getPath(), vo.getOrderNum())
        ).collect(Collectors.toList());
    }


}