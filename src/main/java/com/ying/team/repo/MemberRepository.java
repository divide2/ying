package com.ying.team.repo;

import com.ying.team.model.Member;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * @author bvvy
 */
public interface MemberRepository extends JpaRepository<Member, Integer> {

    /**
     * 用户在团队下
     *
     * @param userId  userid
     * @param teamId teamid
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

}
