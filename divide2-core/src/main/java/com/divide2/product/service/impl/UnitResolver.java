package com.divide2.product.service.impl;

import com.divide2.product.bo.UnitBO;
import com.divide2.product.dto.UnitDTO;
import org.apache.commons.lang3.StringUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

/**
 * @author bvvy
 * @date 2019/4/10
 */
public class UnitResolver {

    private final List<UnitBO> units;

    public UnitResolver(List<UnitDTO> aunits) {
        units = new ArrayList<>();
        aunits.forEach(this::add);
    }

    public UnitResolver() {
        this(new ArrayList<>());
    }

    public void add(UnitDTO dto) {
        String id;
        if (StringUtils.isBlank(dto.getId())) {
            id = UUID.randomUUID().toString();
        } else {
            id = dto.getId();
        }
        if (units.size() > 0) {
            UnitBO child = units.get(units.size() - 1);
            UnitBO unit = new UnitBO(id, dto.getName(), dto.getRate(), child.getId());
            units.add(unit);
        } else {
            UnitBO unit = new UnitBO(id, dto.getName(), 1, StringUtils.EMPTY);
            units.add(unit);
        }
    }

    /**
     * 返回ids
     * @return ids
     */
    public String[] resolve() {
        String[] ids = new String[units.size()];
        List<String> idList = units.stream().map(UnitBO::getId).collect(Collectors.toList());
        return idList.toArray(ids);
    }

    public List<UnitBO> getUnits() {
        return units;
    }
}
