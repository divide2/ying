package com.divide2.basis.service;

import com.divide2.core.basic.service.BasicService;
import com.divide2.basis.model.Dictionary;

import java.util.List;

/**
 * @author zejun
 * @date 2018/7/30 09:19
 */
public interface DictionaryService extends BasicService<Dictionary,Integer> {
    /**
     * 通过group code 和 code 获取dic
     * @param groupCode group code
     * @param code code
     * @return dic
     */
    Dictionary getByGroupCodeAndCode(String groupCode, String code);

    List<Dictionary> findByGroup(String groupCode);
}
