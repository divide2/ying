package com.mj.general.dictionary.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

/**
 * @author zejun
 * @date 2018/7/12 14:37
 */
@Data
@Entity
@Table(name = "general_data")
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class GeneralData {

    /**
     * id
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    /**
     * 分组名
     */
    @Column(name = "name_code")
    private String nameCode;

    /**
     * 中文名
     */
    @Column(name = "chinese_name")
    private String chineseName;

    /**
     * 英文名
     */
    @Column(name = "english_name")
    private String englishName;
}
