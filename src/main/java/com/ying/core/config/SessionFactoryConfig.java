package com.ying.core.config;

import org.hibernate.SessionFactory;
import org.springframework.context.annotation.Configuration;

import javax.persistence.EntityManagerFactory;

/**
 * @author bvvy
 * @date 2018/8/6
 */
@Configuration
public class SessionFactoryConfig {

    public SessionFactory sessionFactory(EntityManagerFactory emf) {
        return emf.unwrap(SessionFactory.class);
    }
}
