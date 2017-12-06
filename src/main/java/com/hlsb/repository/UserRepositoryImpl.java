package com.hlsb.repository;


import org.springframework.stereotype.Repository;

/**
 * Created by bvvy on 2017/12/4.
 */
@Repository
public class UserRepositoryImpl implements UserRepositoryCustom {
    @Override
    public void someCustomMethod(String user) {
        System.out.println("nihao");
    }
}
