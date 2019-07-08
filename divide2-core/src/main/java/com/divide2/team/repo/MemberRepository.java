package com.divide2.team.repo;

import com.divide2.team.model.Member;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * @author bvvy
 */
public interface MemberRepository extends JpaRepository<Member, String> {

    /**
     * 用户在团队下
     *
     * @param userId  userid
     * @param teamId teamid
     * @return member
     */
    Member getByTeamIdAndUserId(String teamId, Integer userId);


    /**
     * 团队成员
     *
     * @param teamId 团队
     * @return info
     */
    List<Member> findByTeamId(String teamId);

    /**
     * 用户的团队
     * @param userId userId
     * @return user
     */
    List<Member> findByUserId(Integer userId);

    /**
     * 获取团队下的小组下的成员
     * @param teamId team
     * @param squadId 小组
     * @return member
     */
    List<Member> findByTeamIdAndSquadId(String teamId, String squadId);
}
