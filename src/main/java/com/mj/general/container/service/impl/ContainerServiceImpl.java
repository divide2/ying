package com.mj.general.container.service.impl;

import com.mj.core.data.properties.StatusProperties;
import com.mj.core.exception.AlreadyExistsException;
import com.mj.core.service.impl.SimpleBasicServiceImpl;
import com.mj.general.container.dto.ContainerCheckDTO;
import com.mj.general.container.dto.ContainerQueryDTO;
import com.mj.general.container.model.Container;
import com.mj.general.container.model.QContainer;
import com.mj.general.container.repo.ContainerRepository;
import com.mj.general.container.service.ContainerService;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.core.types.dsl.Expressions;
import org.apache.commons.lang3.StringUtils;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

/**
 * @author zejun
 * @date 2018/7/26 15:44
 */
@Service
public class ContainerServiceImpl extends SimpleBasicServiceImpl<Container,Integer,ContainerRepository>
        implements ContainerService {
    private final ContainerRepository containerRepository;
    private final StatusProperties status;

    public ContainerServiceImpl(ContainerRepository containerRepository,StatusProperties status) {
        this.containerRepository = containerRepository;
        this.status = status;
    }

    @Override
    public void toggleEnable(Integer id) {
        Container container = this.get(id);
        if (status.getEnable().equals(container.getEnabled())) {
            container.setEnabled(status.getDisable());
        } else {
            container.setEnabled(status.getEnable());
        }
        this.update(container);
    }

    @Override
    public void check(ContainerCheckDTO containerCheckDTO) {
        if (StringUtils.isNotEmpty(containerCheckDTO.getContainerCode())) {
            Container container = containerRepository.getByContainerCodeIgnoreCase(containerCheckDTO.getContainerCode());
            if (container != null) {
                throw new AlreadyExistsException();
            }
        }
    }

    @Override
    public Page<Container> find(ContainerQueryDTO containerQueryDTO, Pageable pageable) {
        QContainer container = QContainer.container;
        BooleanExpression predicate = Expressions.ONE.eq(Expressions.ONE);
        if (StringUtils.isNotEmpty(containerQueryDTO.getContainerCode())) {
            predicate = container.containerCode.eq(containerQueryDTO.getContainerCode());
        }
        return containerRepository.findAll(predicate,pageable);
    }


}
