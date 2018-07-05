package com.mj.auth.res.repo;

import com.mj.auth.res.model.Menu;
import com.mj.auth.res.repo.custom.MenuRepositoryCustom;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @author bvvy
 */
public interface MenuRepository extends JpaRepository<Menu, Integer>,MenuRepositoryCustom {



}
