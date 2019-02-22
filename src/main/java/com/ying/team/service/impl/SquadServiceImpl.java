package com.ying.team.service.impl;

import com.ying.core.basic.service.impl.SimpleBasicServiceImpl;
import com.ying.team.model.Squad;
import com.ying.team.repo.SquadRepository;
import com.ying.team.service.SquadService;
import com.ying.team.vo.SquadVO;
import org.springframework.stereotype.Service;

/**
 * @author bvvy
 */
@Service
public class SquadServiceImpl extends SimpleBasicServiceImpl<Squad, String, SquadRepository> implements SquadService {

    @Override
    public SquadVO getVO(String id) {
        Squad squad = this.get(id);
        return new SquadVO(squad.getId(), squad.getName());
    }

}
