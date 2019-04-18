package com.divide2.team.service.impl;

import com.divide2.core.basic.service.impl.SimpleBasicServiceImpl;
import com.divide2.core.root.converter.Converter;
import com.divide2.team.model.Squad;
import com.divide2.team.repo.SquadRepository;
import com.divide2.team.service.SquadService;
import com.divide2.team.vo.SquadVO;
import org.springframework.stereotype.Service;

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
