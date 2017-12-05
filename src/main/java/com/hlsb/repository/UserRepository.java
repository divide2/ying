package com.hlsb.repository;

import com.hlsb.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;

/**
 * Created by bvvy on 2017/12/4.
 * com.hlsb.repo
 */
public interface UserRepository extends CustomizedUserRepository,CrudRepository<User,Integer>  {
}
