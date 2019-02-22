package com.ying.auth.repo.impl;


import com.querydsl.core.types.Projections;
import com.querydsl.jpa.impl.JPAQuery;
import com.ying.auth.model.*;
import com.ying.auth.repo.cutom.UserRepositoryCustom;
import com.ying.auth.vo.TeamVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import javax.persistence.EntityManager;
import java.util.List;

/**
 * @author bvvy
 */
public class UserRepositoryImpl implements UserRepositoryCustom {

    @Autowired
    private EntityManager entityManager;
    private QUser user = QUser.user;
    private QRole role = QRole.role;
    private QTeam team = QTeam.team;
    private QUserTeamRole ur = QUserTeamRole.userTeamRole;
    private QUserTeamRole utr = QUserTeamRole.userTeamRole;


    @Override
    public List<SimpleGrantedAuthority> findUserRolesByUsername(String username) {

        JPAQuery<SimpleGrantedAuthority> query = new JPAQuery<>(entityManager);
        return query.select(Projections.constructor(SimpleGrantedAuthority.class, role.code))
                .from(user).leftJoin(ur).on(user.id.eq(ur.userId)).leftJoin(role).on(role.id.eq(ur.roleId))
                .where(user.username.eq(username)).fetch();

    }


    @Override
    public List<User> findUsersByRole(Integer roleId) {
        JPAQuery<User> query = new JPAQuery<>(entityManager);
        return query.select(user).from(ur).innerJoin(user).on(ur.userId.eq(user.id))
                .where(ur.roleId.eq(roleId)).fetch();
    }

    @Override
    public User getByAccount(String username) {

        JPAQuery<User> query = new JPAQuery<>(entityManager);
        return query.from(user)
                .where(user.username.eq(username)
                        .or(user.phone.eq(username))
                        .or(user.email.eq(username))).fetchOne();
    }

    @Override
    public List<TeamVO> listUserTeam(Integer userId) {
        JPAQuery<?> query = new JPAQuery<>(entityManager);
        return query.select(Projections.bean(TeamVO.class, team.id, team.name,team.image))
                .from(utr).innerJoin(team).on(utr.teamId.eq(team.id))
                .where(utr.userId.eq(userId)).fetch();
    }

}
