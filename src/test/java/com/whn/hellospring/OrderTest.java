package com.whn.hellospring;

import com.whn.hellospring.model.CoffeeOrderDO;
import com.whn.hellospring.model.CoffeeOrderDTO;
import com.whn.hellospring.service.CoffeeOrderService;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest
@Slf4j
public class OrderTest {

    @Autowired
    CoffeeOrderService orderService;


    /**
     * 根据客户获取订单列表
     */
    @Test
    void getOrderListByCustomer(){
        List<CoffeeOrderDTO> list = orderService.getOrderListByCustomerId("1");
        log.info("list{}",list);
    }



}
