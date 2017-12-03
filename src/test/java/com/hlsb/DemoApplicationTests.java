package com.hlsb;

import com.hlsb.model.User;
import com.hlsb.repo.UserRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class DemoApplicationTests {

    @Autowired
    private UserRepository userRepositoryImpl;

    @Test
    public void contextLoads() {
        User user = User
                .builder()
                .id(4)
                .username("guanyin")
                .nickname("观音")
                .clzId(2)
                .build();
        userRepositoryImpl.save(user);
    }

    @Test
    public void testListAllUser() {
        userRepositoryImpl.listAllUsers().forEach(System.out::print);
    }

    @Test
    public void testUserWithoutPassword() {
        userRepositoryImpl.listUsersWithoutPassword().forEach(System.out::print);
    }

    @Test
    public void listUserCountOfClz() {

        userRepositoryImpl.listUserCountOfClz().forEach(System.out::println);
    }
}
