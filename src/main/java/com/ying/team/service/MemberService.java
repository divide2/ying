package com.ying.team.service;

import com.ying.core.basic.service.BasicService;
import com.ying.team.model.Member;

/**
 * @author bvvy
 * @date 2019/2/13
 */
public interface MemberService extends BasicService<Member,String> {


    String getTeamUserSquadId(String teamId, Integer userId);
}
