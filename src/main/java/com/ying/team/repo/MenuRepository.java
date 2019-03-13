package com.ying.team.repo;


import com.ying.team.model.Menu;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Collection;
import java.util.List;
import java.util.Set;

/**
 * @author bvvy
 */
public interface MenuRepository extends JpaRepository<Menu, String> {

    /**
     * authority
     *
     * @param authority authority
     * @return menu
     */
    Menu getByAuthority(String authority);

    /**
     * in id
     *
     * @param ids ids
     * @return menu
     */
    List<Menu> findByIdIn(Collection<String> ids);

    /**
     * 通过r
     *
     * @param authorities authority
     * @return Menu
     */
    List<Menu> findByAuthorityIn(Set<String> authorities);

    List<Menu> findByShortcutAndAuthorityIn(Boolean shortcut, Collection<String> authorities);

    default List<Menu> findShortcutByAuthorities(Collection<String> authorities) {
        return findByShortcutAndAuthorityIn(true, authorities);
    }

    /**
     * 通过父级菜单获取子菜单
     *
     * @param pid pid
     * @return menu
     */
    default List<Menu> findByPidAndShortcut(String pid, Boolean shortcut) {
        return findByPidAndShortcutOrderByOrderNumAsc(pid, shortcut);
    }



    /**
     * 通过父级菜单获取子菜单 排个序
     *
     * @param pid pid
     * @return menu
     */
    List<Menu> findByPidAndShortcutOrderByOrderNumAsc(String pid, Boolean shortcut);

    List<Menu> findByPidOrderByOrderNumAsc(String pid);

    default List<Menu> findByPid(String pid) {
        return findByPidOrderByOrderNumAsc(pid);
    }
}
