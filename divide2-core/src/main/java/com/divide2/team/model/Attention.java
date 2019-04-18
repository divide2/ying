package com.divide2.team.model;

import com.divide2.core.model.BaseEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Parameter;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import java.time.LocalDateTime;

/**
 * @author bvvy
 * @date 2019/1/4
 */
@EqualsAndHashCode(callSuper = false)
@Table(name = "a_attention")
@Entity
@Data
public class Attention extends BaseEntity {
    @Id
    @GeneratedValue(generator = "custom-uuid")
    @GenericGenerator(
            name = "custom-uuid",
            strategy = "org.hibernate.id.UUIDGenerator",
            parameters = {
                    @Parameter(
                            name = "uuid_gen_strategy_class",
                            value = "org.hibernate.id.uuid.CustomVersionOneStrategy"
                    )
            }
    )
    private String id;
    private String teamId;
    private LocalDateTime createTime;
    private String title;
    private String content;
    /**
     * 用 模板？
     */
    private String type;
}
