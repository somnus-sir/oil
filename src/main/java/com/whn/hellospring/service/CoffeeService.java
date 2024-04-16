package com.whn.hellospring.service;


import com.whn.hellospring.model.CoffeeDO;
import com.whn.hellospring.repository.CoffeeRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

import static org.springframework.data.domain.ExampleMatcher.GenericPropertyMatchers.exact;

@Slf4j
@Service
public class CoffeeService {

    @Autowired
    private CoffeeRepository coffeeRepository;


    /**
     * 根据名称，寻找咖啡
     */
    public Optional<CoffeeDO>  findOneCoffee(String name){
        ExampleMatcher matcher = ExampleMatcher.matching()
                .withMatcher("name",exact().ignoreCase());
        Optional<CoffeeDO> coffee = coffeeRepository.findOne(
                Example.of(CoffeeDO.builder().name(name).build(),matcher));
        log.info("Coffee Found:{}",coffee);
        return coffee;
    }

    /**
     * 创建编辑一杯咖啡
     */
    public CoffeeDO updateCoffee(CoffeeDO coffeeDO){
        CoffeeDO save = coffeeRepository.save(coffeeDO);
        return save;
    }




    /**
     * 根据id 删除一杯咖啡
     */
    public void deleteCoffee(Long id){
        coffeeRepository.deleteById(id);
    }


    /**
     * 获取一个订单下咖啡列表
     */
    public List<CoffeeDO> getCoffeeListWithOrderId(Long orderId){
        List<CoffeeDO> coffeeList = coffeeRepository.selectCoffeeListWithOrder(orderId);
        return coffeeList;
    }

}
