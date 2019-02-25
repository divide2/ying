package com.ying.auth.repo;

import com.ying.auth.repo.custom.AclRepositoryCustom;
import com.ying.team.model.Acl;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @author bvvy
 */
public interface AclRepository extends JpaRepository<Acl, Integer>, AclRepositoryCustom {


    default List<Acl> findByTeamUser(String teamId, Integer userId) {
        return null;
    }
}
