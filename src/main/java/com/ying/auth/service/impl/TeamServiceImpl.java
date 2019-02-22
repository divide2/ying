package com.ying.auth.service.impl;

import com.ying.auth.dto.*;
import com.ying.auth.model.*;
import com.ying.auth.repo.*;
import com.ying.auth.service.TeamInnerConnectService;
import com.ying.auth.service.TeamService;
import com.ying.auth.vo.*;
import com.ying.core.basic.service.impl.SimpleBasicServiceImpl;
import com.ying.core.er.Asserter;
import com.ying.core.er.Loginer;
import com.ying.core.root.converter.Converter;
import com.ying.friend.dto.ChatDTO;
import com.ying.mine.vo.WarehouseVO;
import com.ying.order.query.OrderQueryParam;
import com.ying.order.vo.OrderVO;
import com.ying.product.query.StockQuery;
import com.ying.product.vo.ProductVO;
import com.ying.product.vo.StockVO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

/**
 * @author bvvy
 * @date 2019/2/13
 */
@Service
public class TeamServiceImpl extends SimpleBasicServiceImpl<Team, String, TeamRepository> implements TeamService {
    private final UserTeamRoleRepository userTeamRoleRepository;
    private final TeamRepository teamRepository;
    private final TeamInnerConnectService teamInnerConnectService;
    private final TeamApplicationRepository teamApplicationRepository;
    private final TeamCooperationApplicationRepository teamCooperationApplicationRepository;
    private final TeamCooperationRepository teamCooperationRepository;

    public TeamServiceImpl(UserTeamRoleRepository userTeamRoleRepository,
                           TeamRepository teamRepository,
                           TeamInnerConnectService teamInnerConnectService,
                           TeamApplicationRepository teamApplicationRepository,
                           TeamCooperationApplicationRepository teamCooperationApplicationRepository,
                           TeamCooperationRepository teamCooperationRepository) {
        this.userTeamRoleRepository = userTeamRoleRepository;
        this.teamRepository = teamRepository;
        this.teamInnerConnectService = teamInnerConnectService;
        this.teamApplicationRepository = teamApplicationRepository;
        this.teamCooperationApplicationRepository = teamCooperationApplicationRepository;
        this.teamCooperationRepository = teamCooperationRepository;
    }

    @Override
    public TeamVO getVO(String teamId) {
        Team team = this.get(teamId);
        return toGroupVO(team);
    }

    private TeamVO toGroupVO(Team team) {
        return new TeamVO(
                team.getId(),
                team.getName(),
                team.getImage()
        );
    }

    @Override
    @Transactional
    public void add(TeamAddDTO dto) {
        this.checkGroupExists(dto.getName());
        Team team = new Team();
        team.setName(dto.getName());
        this.add(team);
        UserTeamRole userTeamRole = new UserTeamRole();
        userTeamRole.setUserId(Loginer.userId());
        userTeamRole.setTeamId(team.getId());
        // todo 管理员的id
        userTeamRole.setRoleId(1);
        userTeamRoleRepository.save(userTeamRole);
    }

    private void checkGroupExists(String name) {
        Team team = teamRepository.getByName(name);
        Asserter.isNull(team);
    }


    @Override
    public TeamVO search(UserSearchDTO search) {
        Team team = teamRepository.getByName(search.getQuery());
        if (team == null) {
            return null;
        }
        return toGroupVO(team);
    }

    @Override
    public List<TeamUserVO> listGroupUsers(String teamId) {
        this.getVO(teamId);
        List<UserTeamRole> teamUsers = userTeamRoleRepository.findByTeamId(teamId);
        return Converter.of(teamUsers).convert(this::toGroupUserVO);
    }

    private TeamUserVO toGroupUserVO(UserTeamRole teamUser) {

        UserVO user = teamInnerConnectService.getUser(teamUser.getUserId());
        RoleVO role = teamInnerConnectService.getRole(teamUser.getRoleId());
        return new TeamUserVO(role, user);
    }

    @Override
    public void apply(TeamApplyDTO dto) {

        UserTeamRole existGroupUser = userTeamRoleRepository.getByUserIdAndTeamId(Loginer.userId(), dto.getToTeamId());
        Asserter.isNull(existGroupUser);
        TeamJoinApplication teamJoinApplication = teamApplicationRepository.getByFromIdAndToTeamId(Loginer.userId(), dto.getToTeamId());
        if (teamJoinApplication == null) {
            teamJoinApplication = new TeamJoinApplication();
            teamJoinApplication.setFromId(Loginer.userId());
            teamJoinApplication.setToTeamId(dto.getToTeamId());
            teamJoinApplication.setMemoName(dto.getMemoName());
            teamJoinApplication.setCreateTime(LocalDateTime.now());
            teamJoinApplication.setUpdateTime(LocalDateTime.now());
            teamJoinApplication.setStatus("waiting_confirm");
        } else {
            teamJoinApplication.setStatus("waiting_confirm");
            teamJoinApplication.setUpdateTime(LocalDateTime.now());
        }
        teamApplicationRepository.save(teamJoinApplication);
        // 获取功能菜单 team_join_apply
        MenuVO menu = teamInnerConnectService.getMenu("team_join_apply");

        // 获取该团队下管理这个功能的人
        List<UserVO> users = teamInnerConnectService.listGroupOwnMenuUsers(dto.getToTeamId(), menu.getId());

        users.forEach(user -> teamInnerConnectService.addChat(new ChatDTO(
                user.getUserId(),
                menu.getId().toString(),
                "menu",
                menu.getName(),
                menu.getPath(),
                "你有新的团队申请"
        )));

    }

    @Override
    public void confirm(TeamConfirmDTO dto) {

        TeamJoinApplication teamJoinApplication = teamApplicationRepository.getOne(dto.getTeamApplicationId());
        UserTeamRole existsUser = userTeamRoleRepository.getByUserIdAndTeamId(teamJoinApplication.getFromId(), teamJoinApplication.getToTeamId());
        Asserter.isNull(existsUser);
        teamJoinApplication.setStatus("finish");
        teamJoinApplication.setUpdateTime(LocalDateTime.now());
        teamApplicationRepository.save(teamJoinApplication);
        UserTeamRole userTeamRole = new UserTeamRole();
        userTeamRole.setMemoName(dto.getMemoName());
        userTeamRole.setRoleId(dto.getRoleId());
        userTeamRole.setTeamId(teamJoinApplication.getToTeamId());
        userTeamRole.setUserId(teamJoinApplication.getFromId());
        userTeamRoleRepository.save(userTeamRole);

    }

    @Override
    public List<TeamApplicationVO> listTeamApplications(String teamId) {
        List<TeamJoinApplication> teamJoinApplications = teamApplicationRepository.findByToTeamIdOrderByUpdateTimeDesc(teamId);
        return Converter.of(teamJoinApplications).convert(this::toGroupApplicationVO);
    }

    private TeamApplicationVO toGroupApplicationVO(TeamJoinApplication teamJoinApplication) {

        UserVO user = teamInnerConnectService.getUser(teamJoinApplication.getFromId());
        return new TeamApplicationVO(
                teamJoinApplication.getId(),
                teamJoinApplication.getMemoName(),
                teamJoinApplication.getStatus(),
                user
        );
    }


    @Override
    public void confirmCooperation(TeamCooperationConfirmDTO dto) {
        TeamCooperationApplication application = teamCooperationApplicationRepository.getOne(dto.getId());
        application.setStatus("finish");
        teamCooperationApplicationRepository.save(application);
        checkExistCooperation(application.getFromTeamId(), application.getToTeamId());
        TeamCooperation teamCooperation = new TeamCooperation();
        teamCooperation.setCreateTime(LocalDateTime.now());
        teamCooperation.setFromTeamId(application.getFromTeamId());
        teamCooperation.setToTeamId(application.getToTeamId());
        teamCooperationRepository.save(teamCooperation);
        teamCooperation = new TeamCooperation();
        teamCooperation.setCreateTime(LocalDateTime.now());
        teamCooperation.setFromTeamId(application.getToTeamId());
        teamCooperation.setToTeamId(application.getFromTeamId());
        teamCooperationRepository.save(teamCooperation);

    }

    private void checkExistCooperation(String fromTeamId, String toTeamId) {
        TeamCooperation teamCooperation = teamCooperationRepository.getByFromTeamIdAndToTeamId(fromTeamId, toTeamId);
        Asserter.isNull(teamCooperation);
        teamCooperation = teamCooperationRepository.getByFromTeamIdAndToTeamId(toTeamId, fromTeamId);
        Asserter.isNull(teamCooperation);
    }

    @Override
    public void applyCooperation(TeamCooperationApplyDTO dto) {
        TeamCooperationApplication existApplication = teamCooperationApplicationRepository.getByFromTeamIdAndToTeamId(dto.getToTeamId(), dto.getFromTeamId());
        if (existApplication != null) {
            this.confirmCooperation(new TeamCooperationConfirmDTO(existApplication.getId()));
            return;
        }
        checkExistCooperation(dto.getFromTeamId(), dto.getToTeamId());
        TeamCooperationApplication teamCooperationApplication = teamCooperationApplicationRepository.getByFromTeamIdAndToTeamId(dto.getFromTeamId(), dto.getToTeamId());
        if (teamCooperationApplication == null) {
            teamCooperationApplication = new TeamCooperationApplication();
        }
        teamCooperationApplication.setCreateTime(LocalDateTime.now());
        teamCooperationApplication.setFromTeamId(dto.getFromTeamId());
        teamCooperationApplication.setToTeamId(dto.getToTeamId());
        teamCooperationApplication.setRemarks(dto.getRemarks());
        teamCooperationApplication.setStatus("waiting_confirm");
        teamCooperationApplicationRepository.save(teamCooperationApplication);
    }

    @Override
    public List<CooperationApplicationVO> listTeamCooperationApplication(String teamId) {
        List<TeamCooperationApplication> teamCooperationApplications = teamCooperationApplicationRepository.findTeamCooperationApplications(teamId);
        return Converter.of(teamCooperationApplications).convert(app -> {
            CooperationApplicationVO vo = new CooperationApplicationVO();
            vo.setId(app.getId());
            vo.setRemarks(app.getRemarks());
            vo.setStatus(app.getStatus());
            TeamVO team = null;
            if (app.getFromTeamId().equals(teamId)) {
                team = this.getVO(app.getToTeamId());
                vo.setSelfApply(true);
            }
            if (app.getToTeamId().equals(teamId)) {
                team = this.getVO(app.getFromTeamId());
            }
            vo.setTeam(team);
            return vo;
        });
    }

    @Override
    public List<TeamVO> listTeamCooperations(String teamId) {
        List<TeamCooperation> vos = teamCooperationRepository.findByFromTeamId(teamId);
        return Converter.of(vos).convert(vo -> this.getVO(vo.getToTeamId()));
    }

    @Override
    public List<WarehouseVO> listWarehouse(String teamId) {
        return teamInnerConnectService.listWarehouse(teamId);
    }

    @Override
    public Page<StockVO> findStock(String teamId, StockQuery stockQuery, Pageable pageable) {
        return teamInnerConnectService.findStock(teamId, stockQuery, pageable);
    }

    @Override
    public Page<ProductVO> findProduct(String teamId, Pageable pageable) {
        return teamInnerConnectService.findProduct(teamId, pageable);
    }

    @Override
    public Page<OrderVO> findReceiveOrder(String teamId, OrderQueryParam queryParam, Pageable pageable) {
        return teamInnerConnectService.findReceiveOrder(teamId, queryParam, pageable);
    }

    @Override
    public Page<OrderVO> findSendOrder(String teamId, OrderQueryParam queryParam, Pageable pageable) {
        return teamInnerConnectService.findSendOrder(teamId, queryParam, pageable);
    }
}