package com.ying.auth.service.impl;

import com.ying.auth.dto.GroupAddDTO;
import com.ying.auth.dto.GroupApplyDTO;
import com.ying.auth.dto.GroupConfirmDTO;
import com.ying.auth.dto.UserSearchDTO;
import com.ying.auth.model.Group;
import com.ying.auth.model.GroupApplication;
import com.ying.auth.model.UserGroupRole;
import com.ying.auth.repo.GroupApplicationRepository;
import com.ying.auth.repo.GroupRepository;
import com.ying.auth.repo.UserGroupRoleRepository;
import com.ying.auth.service.GroupInnerConnectService;
import com.ying.auth.service.GroupService;
import com.ying.auth.vo.*;
import com.ying.core.basic.service.impl.SimpleBasicServiceImpl;
import com.ying.core.er.Asserter;
import com.ying.core.er.Loginer;
import com.ying.core.root.converter.Converter;
import com.ying.mine.vo.WarehouseVO;
import com.ying.order.query.OrderQueryParam;
import com.ying.order.vo.OrderVO;
import com.ying.product.model.Warehouse;
import com.ying.product.query.StockQuery;
import com.ying.product.vo.ProductVO;
import com.ying.product.vo.StockVO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author bvvy
 * @date 2019/2/13
 */
@Service
public class GroupServiceImpl extends SimpleBasicServiceImpl<Group, String, GroupRepository> implements GroupService {
    private final UserGroupRoleRepository userGroupRoleRepository;
    private final GroupRepository groupRepository;
    private final GroupInnerConnectService groupInnerConnectService;
    private final GroupApplicationRepository groupApplicationRepository;

    public GroupServiceImpl(UserGroupRoleRepository userGroupRoleRepository,
                            GroupRepository groupRepository,
                            GroupInnerConnectService groupInnerConnectService,
                            GroupApplicationRepository groupApplicationRepository) {
        this.userGroupRoleRepository = userGroupRoleRepository;
        this.groupRepository = groupRepository;
        this.groupInnerConnectService = groupInnerConnectService;
        this.groupApplicationRepository = groupApplicationRepository;
    }

    @Override
    public GroupVO getVO(String groupId) {
        Group group = this.get(groupId);
        return toGroupVO(group);
    }

    private GroupVO toGroupVO(Group group) {
        return new GroupVO(
                group.getId(),
                group.getName(),
                group.getImage()
        );
    }

    @Override
    @Transactional
    public void add(GroupAddDTO dto) {
        this.checkGroupExists(dto.getName());
        Group group = new Group();
        group.setName(dto.getName());
        this.add(group);
        UserGroupRole userGroupRole = new UserGroupRole();
        userGroupRole.setUserId(Loginer.userId());
        userGroupRole.setGroupId(group.getId());
        // todo 管理员的id
        userGroupRole.setRoleId(1);
        userGroupRoleRepository.save(userGroupRole);
    }

    private void checkGroupExists(String name) {
        Group group = groupRepository.getByName(name);
        Asserter.isNull(group);
    }


    @Override
    public GroupVO search(UserSearchDTO search) {
        Group group = groupRepository.getByName(search.getQuery());
        return toGroupVO(group);
    }

    @Override
    public List<GroupUserVO> listGroupUsers(String groupId) {
        this.getVO(groupId);
        List<UserGroupRole> groupUsers = userGroupRoleRepository.findByGroupId(groupId);
        return Converter.of(groupUsers).convert(this::toGroupUserVO);
    }

    private GroupUserVO toGroupUserVO(UserGroupRole groupUser) {

        UserVO user = groupInnerConnectService.getUser(groupUser.getUserId());
        RoleVO role = groupInnerConnectService.getRole(groupUser.getRoleId());
        return new GroupUserVO(role, user);
    }

    @Override
    public void apply(GroupApplyDTO dto) {

        UserGroupRole existGroupUser = userGroupRoleRepository.getByUserIdAndGroupId(Loginer.userId(), dto.getToGroupId());
        Asserter.isNull(existGroupUser);
        GroupApplication groupApplication = groupApplicationRepository.getByFromIdAndToGroupId(Loginer.userId(), dto.getToGroupId());
        if (groupApplication == null) {
            groupApplication = new GroupApplication();
            groupApplication.setFromId(Loginer.userId());
            groupApplication.setToGroupId(dto.getToGroupId());
            groupApplication.setMemoName(dto.getMemoName());
            groupApplication.setCreateTime(LocalDateTime.now());
            groupApplication.setUpdateTime(LocalDateTime.now());
            groupApplication.setStatus("waiting_confirm");
        } else {
           groupApplication.setStatus("waiting_confirm");
            groupApplication.setUpdateTime(LocalDateTime.now());
        }
        groupApplicationRepository.save(groupApplication);
    }
    @Override
    public void confirm(GroupConfirmDTO dto) {

        GroupApplication groupApplication = groupApplicationRepository.getOne(dto.getGroupApplicationId());
        UserGroupRole existsUser = userGroupRoleRepository.getByUserIdAndGroupId(groupApplication.getFromId(), groupApplication.getToGroupId());
        Asserter.isNull(existsUser);
        groupApplication.setStatus("finish");
        groupApplication.setUpdateTime(LocalDateTime.now());
        groupApplicationRepository.save(groupApplication);
        UserGroupRole userGroupRole = new UserGroupRole();
        userGroupRole.setMemoName(dto.getMemoName());
        userGroupRole.setRoleId(dto.getRoleId());
        userGroupRole.setGroupId(groupApplication.getToGroupId());
        userGroupRole.setUserId(groupApplication.getFromId());
        userGroupRoleRepository.save(userGroupRole);

    }

    @Override
    public List<GroupApplicationVO> listGroupApplications(String groupId) {
        List<GroupApplication> groupApplications = groupApplicationRepository.findByToGroupIdOrderByUpdateTimeDesc(groupId);
        return Converter.of(groupApplications).convert(this::toGroupApplicationVO);
    }

    private GroupApplicationVO toGroupApplicationVO(GroupApplication groupApplication) {

        UserVO user = groupInnerConnectService.getUser(groupApplication.getFromId());
        return new GroupApplicationVO(
                groupApplication.getId(),
                groupApplication.getMemoName(),
                groupApplication.getStatus(),
                user
        );
    }

    @Override
    public List<WarehouseVO> listWarehouse(String groupId) {
        return groupInnerConnectService.listWarehouse(groupId);
    }

    @Override
    public Page<StockVO> findStock(String groupId, StockQuery stockQuery, Pageable pageable) {
        return groupInnerConnectService.findStock(groupId, stockQuery, pageable);
    }

    @Override
    public Page<ProductVO> findProduct(String groupId, Pageable pageable) {
        return groupInnerConnectService.findProduct(groupId, pageable);
    }

    @Override
    public Page<OrderVO> findReceiveOrder(String groupId, OrderQueryParam queryParam, Pageable pageable) {
        return groupInnerConnectService.findReceiveOrder(groupId, queryParam, pageable);
    }

    @Override
    public Page<OrderVO> findSendOrder(String groupId, OrderQueryParam queryParam, Pageable pageable) {
        return groupInnerConnectService.findSendOrder(groupId, queryParam, pageable);
    }
}
