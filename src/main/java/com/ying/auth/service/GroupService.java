package com.ying.auth.service;

import com.ying.auth.dto.GroupAddDTO;
import com.ying.auth.model.Group;
import com.ying.core.basic.service.BasicService;

/**
 * @author bvvy
 * @date 2019/2/13
 */
public interface GroupService extends BasicService<Group,String> {

    void add(GroupAddDTO dto);

}
