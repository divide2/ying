package com.ying.team.service;

import com.ying.core.basic.service.BasicService;
import com.ying.team.model.Squad;
import com.ying.team.vo.SquadVO;

/**
 * @author bvvy
 */
public interface SquadService extends BasicService<Squad, String> {


    SquadVO getVO(String squadId);
}
