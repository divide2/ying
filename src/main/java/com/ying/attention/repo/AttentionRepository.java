package com.ying.attention.repo;

import com.ying.attention.model.Attention;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @author bvvy
 * @date 2019/1/23
 */
public interface AttentionRepository extends JpaRepository<Attention, Integer> {

}
