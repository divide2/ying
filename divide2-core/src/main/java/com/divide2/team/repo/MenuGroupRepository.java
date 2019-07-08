package com.divide2.team.repo;

import com.divide2.team.model.MenuGroup;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * @author bvvy
 * @date 2019/2/26
 */
public interface MenuGroupRepository extends JpaRepository<MenuGroup,String> {
    default List<MenuGroup> findByTeamId(String teamId) {
        return findByTeamIdOrderByOrderNumAsc(teamId);
    }

    List<MenuGroup> findByTeamIdOrderByOrderNumAsc(String teamId);
}
