package com.divide2.product.repo;

import com.divide2.product.model.Unit;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * @author bvvy
 * @date 2019/4/10
 */
public interface UnitRepository extends JpaRepository<Unit,String> {


    List<Unit> findByIdIn(List<String> ids);
}
