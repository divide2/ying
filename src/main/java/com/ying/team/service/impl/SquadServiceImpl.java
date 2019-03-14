package com.ying.team.service.impl;

import com.ying.core.basic.service.impl.SimpleBasicServiceImpl;
import com.ying.core.root.converter.Converter;
import com.ying.team.model.Squad;
import com.ying.team.repo.SquadRepository;
import com.ying.team.service.SquadService;
import com.ying.team.vo.SquadVO;
import org.springframework.stereotype.Service;

import javax.persistence.Convert;
import java.util.List;

/**
 * @author bvvy
 */
@Service
public class SquadServiceImpl extends SimpleBasicServiceImpl<Squad, String, SquadRepository> implements SquadService {
    private final SquadRepository squadRepository;

    public SquadServiceImpl(SquadRepository squadRepository) {
        this.squadRepository = squadRepository;
    }

    @Override
    public SquadVO getVO(String id) {
        Squad squad = this.get(id);
        return new SquadVO(squad.getId(), squad.getName());
    }

    @Override
    public List<SquadVO> listByTeam(String teamId) {
        return Converter.of(squadRepository.findByTeamId(teamId))
                .convert(squad -> new SquadVO(squad.getId(), squad.getName()));
    }

}
