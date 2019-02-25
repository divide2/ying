package com.ying.team.repo;


import com.ying.team.model.Menu;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Set;

/**
 * @author bvvy
 */
public interface MenuRepository extends JpaRepository<Menu, String> {

    /**
     * code
     *
     * @param menuCode code
     * @return menu
     */
    Menu getByCode(String menuCode);

    /**
     * in id
     *
     * @param ids ids
     * @return menu
     */
    List<Menu> findByIdIn(Set<String> ids);
}
