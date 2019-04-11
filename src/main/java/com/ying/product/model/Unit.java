package com.ying.product.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import java.time.LocalDateTime;

/**Lo
 * @author bvvy
 * @date 2019/4/3
 */
@Entity
@Table(name = "p_unit")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Unit {


    @Id
    private String id;

    private String name;

    private Integer rate;

    private String child;

    private LocalDateTime createTime;

    private String teamId;

}
