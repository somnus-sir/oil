package com.whn.hellospring.controller;

import com.whn.hellospring.model.CoffeeDO;
import com.whn.hellospring.model.CoffeeDTO;
import com.whn.hellospring.request.DeleteCoffeeRequest;
import com.whn.hellospring.request.GetCoffeeListByOrderIdRequest;
import com.whn.hellospring.service.CoffeeService;
import com.whn.hellospring.utils.DO2DTOUtil;
import org.joda.money.CurrencyUnit;
import org.joda.money.Money;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping(value = "/coffee")
public class CoffeeController {

    @Autowired
    CoffeeService service;

    /**
     * 创建编辑咖啡
     */
    @PostMapping(value = "/update")
    public String createCoffee(@Valid @RequestBody CoffeeDTO coffeeDTO){
        CoffeeDO coffeeDO = (CoffeeDO) DO2DTOUtil.getInstance().dtoToDo(coffeeDTO, CoffeeDO.class);
        coffeeDO.setPrice(Money.of(CurrencyUnit.of("CNY"),coffeeDTO.getPrice()));
        service.updateCoffee(coffeeDO);
        return "success";
    }


    /**
     * 删除咖啡
     */
    @PostMapping(value = "/delete")
    public String deleteCoffeeById(@RequestBody DeleteCoffeeRequest request){
        service.deleteCoffee(request.getId());
        return "success";
    }


    /**
     * 获取一个订单下的咖啡列表
     * @return
     */
    @PostMapping(value = "/coffeeListByOrderId")
    public List<CoffeeDTO> getCoffeeListByOrderId(@RequestBody GetCoffeeListByOrderIdRequest request){
        List<CoffeeDO> coffeeList = service.getCoffeeListWithOrderId(request.getOrderId());

        List<CoffeeDTO> coffeeDTOList = new ArrayList<>();
        for (CoffeeDO coffeeDo :coffeeList) {
            CoffeeDTO dto = new CoffeeDTO(coffeeDo);
            coffeeDTOList.add(dto);
        }
        return coffeeDTOList;
    }
}
