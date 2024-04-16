package com.whn.hellospring.repository;

import com.whn.hellospring.model.CustomerDO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface CustomerRepository extends JpaRepository<CustomerDO,Long> {


    /**
     * 是否存在该手机号
     */
    @Query(value = "select * from t_customer where phone=?1", nativeQuery = true)
    List<CustomerDO> isHaveThisPhone(String phone);




}
