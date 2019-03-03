package com.ying.team.repo;

import com.ying.team.model.Acl;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * @author bvvy
 */
public interface AclRepository extends JpaRepository<Acl, Integer> {
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
     * @param teamId  teamId
     * @param squadId 小组的
     * @return acls
     */
    default List<Acl> findByTeamSquad(String teamId, String squadId) {
        return findByTeamIdAndPrincipleIdAndPrincipleType(teamId, squadId, Acl.SQUAD_TYPE);
    }


    /**
     * 获取拥有菜单权限的 principle
     *
     * @param teamId team
     * @param authority authority
     * @return acl
     */
    List<Acl> findByTeamIdAndAuthority(String teamId, String authority);

    /**
     * 删除已有的
     *
     * @param teamId        team
     * @param principleId   pid
     * @param principleType ptype
     */
    void deleteByTeamIdAndPrincipleIdAndPrincipleType(String teamId, String principleId, String principleType);

    /**
     * 删除已有的
     * @param teamId team
     * @param pid pid
     * @param ptype ptype
     */
    default void deleteExists(String teamId, String pid, String ptype) {
        this.deleteByTeamIdAndPrincipleIdAndPrincipleType(teamId, pid, ptype);
    }
}
