package com.whn.hellospring;

import com.whn.hellospring.model.CustomerDO;
import com.whn.hellospring.repository.CustomerRepository;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest
@Slf4j
public class CustomerTest {

    @Autowired
    CustomerRepository customerRepository;

    /**
     * 新增一个顾客
     */
    @Test
    void createCustomer(){
        CustomerDO customerDO = CustomerDO.builder().age(18).name("李四").phone("18657545213").pass_word("123456").build();
        customerRepository.save(customerDO);
    }

    /**
     * 获取顾客列表
     */
    @Test
    void queryCustomerList(){
        log.info("CustomerList{}",customerRepository.findAll());
    }


}
