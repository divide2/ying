package com.mj.auth.res.service.impl;

import com.mj.auth.res.dto.OperAddDTO;
import com.mj.auth.res.model.Menu;
import com.mj.auth.res.model.Oper;
import com.mj.auth.res.repo.MenuRepository;
import com.mj.auth.res.repo.OperRepository;
import com.mj.auth.res.service.OperService;
import com.mj.auth.res.val.MenuType;
import com.mj.core.basic.service.impl.SimpleBasicServiceImpl;
import com.mj.core.val.Punctuation;
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
