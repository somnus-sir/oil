package com.whn.hellospring.model;

import lombok.*;

import java.io.Serializable;
import java.util.Date;
import java.util.List;


@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CoffeeOrderDTO implements Serializable {
    private String customer;
    private String customer_id;

    private Long id;
    private Date createTime;
    private Date updateTime;
    private OrderState state;

    private List<CoffeeDTO> items;

}

