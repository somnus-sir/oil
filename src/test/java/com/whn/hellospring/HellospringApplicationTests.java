package com.whn.hellospring;

import com.whn.hellospring.model.*;
import com.whn.hellospring.repository.CoffeeOrderRepository;
import com.whn.hellospring.repository.CoffeeRepository;
import com.whn.hellospring.utils.DO2DTOUtil;
import org.joda.money.CurrencyUnit;
import org.joda.money.Money;
import org.junit.jupiter.api.Test;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@SpringBootTest
class HellospringApplicationTests {

//    @Autowired
//    private CoffeeRepository coffeeRepository;
//
//    @Autowired
//    private CoffeeOrderRepository orderRepository;
//
//
//    /**
//     * 查询一个订单信息，包含咖啡
//     */
//    @Test
//    void queryOrder() {
//        Long id = 8L;
//        CoffeeOrderDO orderDO = orderRepository.queryOrderWithId(id);
//        CoffeeOrderDTO orderDTO = (CoffeeOrderDTO) doToDto(orderDO, CoffeeOrderDTO.class);
//        List<CoffeeDO> coffeeDOList = coffeeRepository.selectCoffeeListWithOrder(id);
//        List<CoffeeDTO> coffeeDTOList = (List<CoffeeDTO>) DO2DTOUtil.getInstance().doListToDtoList(coffeeDOList, CoffeeDTO.class);
//
//        orderDTO.setItems(coffeeDTOList);
//        System.out.println("顾客：" + orderDTO.getCustomer());
//        for (CoffeeDTO coffee :
//                orderDTO.getItems()) {
//            System.out.println("咖啡：" + coffee.getName());
//        }
//    }
//
//
//    /**
//     * 创建一个订单，包含咖啡
//     */
//    @Test
//    void createOrderWithCoffee() {
//        //创建一个订单，包含一杯咖啡
//        CoffeeDO espresso = CoffeeDO.builder().name("订单下的咖啡3")
//                .price(Money.of(CurrencyUnit.of("CNY"), 10.0))
//                .build();
//        coffeeRepository.save(espresso);
//        CoffeeOrderDTO orderDTO = CoffeeOrderDTO.builder()
//                .customer("me3")
//                .state(OrderState.INIT)
//                .build();
//
//
//        //保存订单，拆解DTO为DO
//        CoffeeOrderDO order = (CoffeeOrderDO) dtoToDo(orderDTO, CoffeeOrderDO.class);
//        orderRepository.save(order);
//
//        //保存咖啡
//        List<CoffeeDTO> list = orderDTO.getItems();
//        for (CoffeeDTO coffee : list) {
//            coffee.setOrder_id_fk(order.getId());
////            coffeeRepository.save(coffee);
//        }
//    }
//
//    /**
//     * 创建一杯咖啡
//     */
//    @Test
//    void createCoffee() {
//        CoffeeDO espresso = CoffeeDO.builder().name("espresso")
//                .price(Money.of(CurrencyUnit.of("CNY"), 20.0))
//                .build();
//        coffeeRepository.save(espresso);
//    }
//
//    /**
//     * 获取一个订单下的咖啡列表
//     */
//    @Test
//    void getCoffeeListWithOrder() {
//        List<CoffeeDO> list = coffeeRepository.selectCoffeeListWithOrder(1L);
//        System.out.println("列表长度：" + list.size());
//        for (CoffeeDO item : list) {
//            System.out.println("列表：" + item.getName());
//        }
//    }
//
//
//    /**
//     * DTO模型转换成DO
//     *
//     * @param objectDto DTO 对象
//     * @param doClass   DO 类型
//     * @return doClass类型的对象
//     */
//    public Object dtoToDo(Object objectDto, Class doClass) {
//        if (objectDto == null) {
//            return null;
//        }
//        Object objectDo = null;
//        try {
//            objectDo = doClass.newInstance();
//            BeanUtils.copyProperties(objectDto, objectDo);
//        } catch (Exception ex) {
//            ex.printStackTrace();
//        }
//        return objectDo;
//    }
//
//
//    /**
//     * DO集合转换成DTO集合
//     *
//     * @param doList   DO 对象集合
//     * @param dtoClass DTO 类型
//     * @return dtoClass类型的集合
//     */
//    public Object doListToDtoList(Object doList, Class dtoClass) {
//        if (doList == null) {
//            return null;
//        }
//        List<Object> dtoList = new ArrayList<>();
//        for (Object i : (List) doList) {
//            Object dto = doToDto(i, dtoClass);
//            if (dto != null) {
//                dtoList.add(dto);
//            }
//        }
//        return dtoList;
//    }
//
//    /**
//     * DO转换成DTO
//     *
//     * @param objectDo DO 对象
//     * @param dtoClass DTO 类型
//     * @return dtoClass类型的对象
//     */
//    public Object doToDto(Object objectDo, Class dtoClass) {
//        if (objectDo == null) {
//            return null;
//        }
//        Object objectDto = null;
//        try {
//            objectDto = dtoClass.newInstance();
//            BeanUtils.copyProperties(objectDo, objectDto);
//        } catch (Exception ex) {
//            ex.printStackTrace();
//        }
//        return objectDto;
//    }
}
