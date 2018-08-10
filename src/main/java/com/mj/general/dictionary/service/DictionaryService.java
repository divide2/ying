package com.mj.general.dictionary.service;

import com.mj.core.basic.service.BasicService;
import com.mj.general.dictionary.model.Dictionary;

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
}
