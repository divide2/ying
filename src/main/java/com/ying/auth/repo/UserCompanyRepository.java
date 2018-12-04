package com.ying.auth.repo;

import com.ying.auth.model.UserCompany;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @author bvvy
 * @date 2018/12/4
 */
public interface UserCompanyRepository extends JpaRepository<UserCompany,Integer> {
    /**
     *
     * @param userId userID
     * @return userCompany
     */
    UserCompany getByUserId(Integer userId);
}
