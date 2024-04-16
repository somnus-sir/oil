package com.whn.hellospring.model;

import lombok.*;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

@Entity
@Table(name = "T_ORDER")
@Data
@ToString(callSuper = true)
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CoffeeOrderDO extends BaseEntity implements Serializable {

    private String customer;
    private String customer_id;

//    //关系映射表
//    @ManyToMany
//    private List<CoffeeDO> items;

    @Column(nullable = false)
    private OrderState state;

}

