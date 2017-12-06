package com.hlsb.repository;

import com.hlsb.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Created by bvvy on 2017/12/4.
 * com.hlsb.repo
 */
public interface UserRepository extends UserRepositoryCustom,JpaRepository<User,Integer> {
}
