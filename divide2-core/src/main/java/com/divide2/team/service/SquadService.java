package com.divide2.team.service;

import com.divide2.core.basic.service.BasicService;
import com.divide2.team.model.Squad;
import com.divide2.team.vo.SquadVO;

import java.util.List;

/**
 * @author bvvy
 */
public interface SquadService extends BasicService<Squad, String> {


    SquadVO getVO(String squadId);

    List<SquadVO> listByTeam(String teamId);

}
