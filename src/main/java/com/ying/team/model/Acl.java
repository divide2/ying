package com.ying.team.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;

/**
 * @author bvvy
 */
@Entity
@Table(name = "t_acl")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Acl {

    public static final String USER_TYPE = "user";
    public static final String SQUAD_TYPE = "squad";

    @Id
    @GeneratedValue(generator = "uuid")
    @GenericGenerator(name = "uuid", strategy = "uuid")
    private String id;

    private String teamId;

    /**
     * squad id or user id
     */
    private String principleId;

    /**
     * squad or user
     */
    private String principleType;

    /**
     * code
     */
    private String menuId;

    public boolean isSquadType() {
        return principleType.equals(SQUAD_TYPE);
    }

    public boolean isUserType() {
        return principleType.equals(USER_TYPE);
    }

}
