package com.ying.product.repo;

import com.ying.product.model.Category;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @author bvvy
 * @date 2019/3/8
 */
public interface CategoryRepository extends JpaRepository<Category, String> {

}
