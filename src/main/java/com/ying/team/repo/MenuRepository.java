package com.ying.team.repo;


import com.ying.team.model.Menu;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @author bvvy
 */
public interface MenuRepository extends JpaRepository<Menu, String> {

    Menu getByCode(String menuCode);
}
