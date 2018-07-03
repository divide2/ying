package com.mj.auth.user.repo;


import com.mj.auth.user.model.User;
import com.mj.auth.user.repo.cutom.UserRepositoryCustom;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends UserRepositoryCustom, JpaRepository<User, Integer> {

    User getByUsername(String username);
}
