package com.ying.auth.repo;

import com.ying.auth.model.Group;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @author bvvy
 * @date 2019/2/13
 */
public interface GroupRepository extends JpaRepository<Group, String> {

    Group getByName(String name);
}
