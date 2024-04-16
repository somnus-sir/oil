package com.whn.hellospring.model;

import lombok.*;
import org.hibernate.annotations.Type;
import org.joda.money.Money;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.io.Serializable;
import java.util.Date;


@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class CoffeeDTO implements Serializable {
    private Long order_id_fk;
    private String name;
    private Date createTime;
    private Date updateTime;
    private Double price;

    public CoffeeDTO(CoffeeDO coffeeDO){
        this.createTime = coffeeDO.getCreateTime();
        this.updateTime = coffeeDO.getUpdateTime();
        this.name = coffeeDO.getName();
        this.order_id_fk = coffeeDO.getOrder_id_fk();
        this.price = new Double(coffeeDO.getPrice().getAmount().toString());
    }
}

