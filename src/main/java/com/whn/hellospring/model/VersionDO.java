package com.whn.hellospring.model;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Service;

import javax.persistence.Entity;
import javax.persistence.Table;
import java.io.Serializable;

@Data
@Table(name = "t_version")
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
public class VersionDO extends BaseEntity implements Serializable {


    private int equipment;

    private String version_code;

    private String version_name;


}
