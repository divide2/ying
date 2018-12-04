package com.ying.auth.repo;

import com.ying.auth.model.Menu;
import com.ying.auth.repo.custom.MenuRepositoryCustom;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @author bvvy
 */
public interface MenuRepository extends JpaRepository<Menu, Integer>,MenuRepositoryCustom {

}
