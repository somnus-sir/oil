package com.whn.hellospring.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.*;
import org.hibernate.annotations.Sort;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.io.Serializable;
import java.util.Date;

@Entity
@Table(name = "T_OIL")
@Data
@ToString(callSuper = true)
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class OilDO extends BaseEntity implements Serializable {

    private Double unitPrice;
    private Double num;
    private Double price;
    private Double intervalOilWear;
    private Double fuelOneDay;
    private int mileage;
    private int intervalMileage;
    private String address;
    private String name;
    private int status;
    private Long betweenDay;
    private Long customer_id;

    @JsonFormat(timezone = "GMT", pattern = "yyyy年MM月dd日")
    private Date time;


}

