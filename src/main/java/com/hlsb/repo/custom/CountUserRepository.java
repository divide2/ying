package com.hlsb.repo.custom;

import com.hlsb.model.UserCountOfClz;
        import org.springframework.data.jpa.repository.Query;

/**
 * Created by bvvy on 2017/12/3.
 * com.hlsb.repo.custom
 */
public interface CountUserRepository {
    @Query(value = "select count(*) as userNum,u.clz_name as clzName from t_user u GROUP BY u.clz_id",nativeQuery = true)
    Iterable<UserCountOfClz> listUserCountOfClz();
}
