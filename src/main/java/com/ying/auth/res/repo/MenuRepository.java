package com.ying.auth.res.repo;

import com.ying.auth.res.model.Menu;
import com.ying.auth.res.repo.custom.MenuRepositoryCustom;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @author bvvy
 */
public interface MenuRepository extends JpaRepository<Menu, Integer>,MenuRepositoryCustom {

}
