package com.ying.auth.repo;

import com.ying.auth.repo.custom.AclRepositoryCustom;
import com.ying.team.model.Acl;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * @author bvvy
 */
public interface AclRepository extends JpaRepository<Acl, Integer>, AclRepositoryCustom {
    /**
     * 获取principle的acls
     *
     * @param teamId        teamId
     * @param principleId   principleId
     * @param principleType principleType;
     * @return acls
     */
    List<Acl> findByTeamIdAndPrincipleIdAndPrincipleType(String teamId, String principleId, String principleType);

    /**
     * 获取用户的访问控制
     *
     * @param teamId teamId
     * @param userId userId
     * @return acls
     */
    default List<Acl> findByTeamUser(String teamId, Integer userId) {
        return findByTeamIdAndPrincipleIdAndPrincipleType(teamId, userId.toString(), Acl.USER_TYPE);
    }

    /**
     * 获取用户的访问控制
     *
     * @param teamId teamId
     * @param squadId 小组的
     * @return acls
     */
    default List<Acl> findByTeamSquad(String teamId,String squadId) {
        return findByTeamIdAndPrincipleIdAndPrincipleType(teamId, squadId, Acl.SQUAD_TYPE);
    }


    /**
     * 获取拥有菜单权限的 principle
     * @param teamId team
     * @param menuId menu
     * @return acl
     */
    List<Acl> findByTeamIdAndMenuId(String teamId, String menuId);
}
