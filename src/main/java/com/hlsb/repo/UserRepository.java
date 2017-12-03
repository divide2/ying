package com.hlsb.repo;

import com.hlsb.model.User;
import com.hlsb.model.UserWithoutPassword;
import com.hlsb.repo.custom.CountUserRepository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.querydsl.QueryDslPredicateExecutor;

/**
 * Created by bvvy on 2017/12/3.
 * UserRepository
 */

public interface UserRepository extends JpaRepository<User, Integer>,CountUserRepository,QueryDslPredicateExecutor<User>{

    @Query(value = "select u.* from t_user u", nativeQuery = true)
    Iterable<User> listAllUsers();



    @Query(value = "select  u.id,u.username,u.nickname from t_user u",nativeQuery = true)
    Iterable<UserWithoutPassword> listUsersWithoutPassword();


}
