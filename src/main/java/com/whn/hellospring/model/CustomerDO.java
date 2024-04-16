package com.whn.hellospring.model;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.*;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;

@Entity
@Table(name = "T_CUSTOMER")
@Builder
@Data
@ToString(callSuper = true)
@NoArgsConstructor
@AllArgsConstructor
@JsonIgnoreProperties(value = {"hibernateLazyInitializer","handler"})
public class CustomerDO extends BaseEntity implements Serializable {

    private String name;

    private int age;

    private String phone;

    private String pass_word;
}
