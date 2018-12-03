package com.ying.auth.service.impl;

import com.ying.auth.dto.OperAddDTO;
import com.ying.auth.model.Menu;
import com.ying.auth.model.Oper;
import com.ying.auth.repo.MenuRepository;
import com.ying.auth.repo.OperRepository;
import com.ying.auth.service.OperService;
import com.ying.auth.val.MenuType;
import com.ying.core.basic.service.impl.SimpleBasicServiceImpl;
import com.ying.core.val.Punctuation;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

/**
 * @author bvvy
 */
@Service
public class OperServiceImpl extends SimpleBasicServiceImpl<Oper, Integer, OperRepository> implements OperService {

    private final OperRepository operRepository;
    private final  MenuRepository menuRepository;

    public OperServiceImpl(OperRepository operRepository,
                           MenuRepository menuRepository) {
        this.operRepository = operRepository;
        this.menuRepository = menuRepository;
    }

    @Override
    public void add(OperAddDTO operDTO) {
        Oper oper = Oper.builder()
                .name(operDTO.getName())
                .code(operDTO.getCode())
                .resId(operDTO.getResId())
                .apis(StringUtils.join(operDTO.getApis(),Punctuation.VERTICAL))
                .build();
        this.add(oper);
        Menu menu = new Menu();
        menu.setType(MenuType.OPER);
        menu.setEnabled(true);
        menu.setPid(operDTO.getResId());
        menu.setName(operDTO.getName());
        menuRepository.save(menu);
    }

}
