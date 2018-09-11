package com.ying.basis.star.vo;

import com.ying.basis.star.model.Star;
import lombok.Data;

import java.time.LocalDateTime;

/**
 * @author bvvy
 * @date 2018/9/11
 */
@Data
public class StarVO {

    private Integer id;
    private String toName;
    private String fromName;
    private String type;
    private Integer fromId;
    private String fromAvatar;
    private LocalDateTime cdt;

    public static StarVO  of(Star star) {
        StarVO vo = new StarVO();
        vo.setId(star.getId());
        vo.setToName(star.getToName());
        vo.setFromName(star.getFromName());
        vo.setType(star.getType());
        vo.setFromId(star.getFromId());
        vo.setFromAvatar(star.getFromAvatar());
        vo.setCdt(star.getCdt());
        return vo;
    }
}
