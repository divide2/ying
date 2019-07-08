package com.divide2.product.repo;

import com.divide2.product.model.Category;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @author bvvy
 * @date 2019/3/8
 */
public interface CategoryRepository extends JpaRepository<Category, String> {

}
