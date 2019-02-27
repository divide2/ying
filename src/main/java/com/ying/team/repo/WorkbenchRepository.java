package com.ying.team.repo;

import com.ying.team.model.Workbench;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;

import java.util.List;
import java.util.Set;

/**
 * @author bvvy
 * @date 2019/2/26
 */
public interface WorkbenchRepository extends JpaRepository<Workbench, String> {


    List<Workbench> findByTeamIdOrderByOrderNumAsc(String teamId);

    default List<Workbench> findByTeamId(String teamId) {
        return findByTeamIdOrderByOrderNumAsc(teamId);
    }

    default List<Workbench> findByTeamIdAndMenuIds(String teamId, Set<String> menuIds) {
        return this.findByTeamIdAndMenuIdInOrderByOrderNumAsc(teamId, menuIds);
    }

    List<Workbench> findByTeamIdAndMenuIdInOrderByOrderNumAsc(String teamId, Set<String> menuIds);

    @Modifying
    void deleteByTeamIdAndMenuGroupIdAndMenuId(String teamId, String menuGroupId, String menuId);

    @Modifying
    void deleteByTeamIdAndMenuGroupId(String teamId, String menuGroupId);
}
