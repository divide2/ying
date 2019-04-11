package com.ying.product.service.impl;

import com.ying.product.bo.UnitBO;
import com.ying.product.dto.UnitDTO;
import com.ying.product.model.Unit;
import org.apache.commons.lang3.StringUtils;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

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
        return (String[]) units.stream().map(UnitBO::getId).toArray();
    }

    public List<UnitBO> getUnits() {
        return units;
    }
}
