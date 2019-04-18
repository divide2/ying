package com.divide2.team.service;

import com.divide2.core.basic.service.BasicService;
import com.divide2.team.model.Member;

/**
 * @author bvvy
 * @date 2019/2/13
 */
public interface MemberService extends BasicService<Member,String> {


    String getTeamUserSquadId(String teamId, Integer userId);
}
