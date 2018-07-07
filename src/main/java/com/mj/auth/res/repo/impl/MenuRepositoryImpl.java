package com.mj.auth.res.repo.impl;

import com.mj.auth.res.model.Menu;
import com.mj.auth.res.model.QMenu;
import com.mj.auth.res.repo.custom.MenuRepositoryCustom;
import com.mj.core.data.tree.TreeMerger;
import com.querydsl.core.types.Projections;
import com.querydsl.jpa.impl.JPAQuery;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import java.util.List;

/**
 * @author bvvy
 */
@Repository
public class MenuRepositoryImpl implements MenuRepositoryCustom {

    private final EntityManager entityManager;

    public MenuRepositoryImpl(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    @Override
    public List<TreeMerger> findMenuTree() {
        return findMenuTree(null);
    }

    public List<TreeMerger> findLeftMenuTree() {
        return findMenuTree(Menu.RES_TYPE);
    }

    private List<TreeMerger> findMenuTree(String menuType) {
        QMenu menu = QMenu.menu;
        JPAQuery<TreeMerger> query = new JPAQuery<>(entityManager);
        query.select(Projections.constructor(TreeMerger.class, menu.id, menu.name, menu.pid))
                .from(menu);
        if (menuType != null && !"".equals(menuType)) {
            query.where(menu.menuType.eq(menuType));
        }
        return query.fetch();
    }
}
