package com.whn.hellospring.controller;

import com.whn.hellospring.model.CoffeeOrderDTO;
import com.whn.hellospring.request.DeleteOrderRequest;
import com.whn.hellospring.request.GetOrderRequest;
import com.whn.hellospring.request.getOrderListWithCustomerIdRequest;
import com.whn.hellospring.service.CoffeeOrderService;
import com.whn.hellospring.service.CoffeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping(value = "/order")
public class CoffeeOrderController extends BaseController{

    @Autowired
    CoffeeOrderService orderService;

    @Autowired
    CoffeeService coffeeService;


    /**
     * 创建一个订单
     */
    @PostMapping(value = "/create")
    public String createOrder(@Valid @RequestBody CoffeeOrderDTO coffeeOrderDTO){
        orderService.createOrder(coffeeOrderDTO);
        return "success";
    }

    /**
     * 获取一个订单根据orderId
     */
    @PostMapping(value = "/getOrder")
    public CoffeeOrderDTO getOrderById(@RequestBody GetOrderRequest request){
        CoffeeOrderDTO orderDTO = orderService.getOrderById(request.getId());
        return orderDTO;
    }

    /**
     * 订单删除
     */
    @PostMapping(value = "/delete")
    public String deleteOrderById(@RequestBody DeleteOrderRequest request){
        orderService.deleteOrderById(request.getId());
        return "success";
    }

    /**
     * 根据客户id获取订单列表
     */
    @PostMapping(value = "/getOrderListWithCustomerId")
    public List<CoffeeOrderDTO> getOrderListWithCustomerId(@RequestBody getOrderListWithCustomerIdRequest request){
        List<CoffeeOrderDTO> orderListByCustomerId = orderService.getOrderListByCustomerId(request.getCustomerId());
        return orderListByCustomerId;
    }



}
