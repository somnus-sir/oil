package com.whn.hellospring.model;

import lombok.*;

import javax.persistence.Entity;
import javax.persistence.Table;
import java.io.Serializable;

/**
 * 菜单
 */
@Entity
@Table(name = "T_MENU")
@Data
@ToString(callSuper = true)
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class MenuDO extends BaseEntity implements Serializable {

    private String type;
    private String coffee_name;
    private String coffee_id;
    private String price;

}
