package com.ying.auth.repo;

import com.ying.auth.repo.custom.AclRepositoryCustom;
import com.ying.team.model.Acl;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author bvvy
 */
public interface AclRepository extends JpaRepository<Acl, Integer>, AclRepositoryCustom {


    void findByTeamIdAndMenuIdAndPrincipleType(String teamId, Integer menuId, String squad);

}
