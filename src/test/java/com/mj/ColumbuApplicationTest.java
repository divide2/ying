package com.mj;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * @author bvvy
 * @date 2018/8/9
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class ColumbuApplicationTest {

    @Test
    public void a() {
        Assert.assertEquals(1, 1);
        System.out.println("sdf");
    }
}