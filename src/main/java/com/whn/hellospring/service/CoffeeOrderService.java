package com.whn.hellospring.service;

import com.whn.hellospring.model.*;
import com.whn.hellospring.repository.CoffeeOrderRepository;
import com.whn.hellospring.repository.CoffeeRepository;
import com.whn.hellospring.utils.DO2DTOUtil;
import lombok.extern.slf4j.Slf4j;
import org.joda.money.CurrencyUnit;
import org.joda.money.Money;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Slf4j
@Service
public class CoffeeOrderService {

    @Autowired
    private CoffeeOrderRepository orderRepository;

    @Autowired
    private CoffeeRepository coffeeRepository;

    /**
     * 删除一个订单
     */
    public void deleteOrderById(Long id) {
        //删除订单
        orderRepository.deleteOrderWithId(id);
        //删除订单下的咖啡
        coffeeRepository.deleteCoffeeByOrderId(id);
    }

    /**
     * 创建一个订单
     */
    public void createOrder(CoffeeOrderDTO coffeeOrderDTO) {
        //保存订单
        CoffeeOrderDO order = (CoffeeOrderDO) DO2DTOUtil.getInstance().dtoToDo(coffeeOrderDTO, CoffeeOrderDO.class);
        order.setState(OrderState.INIT);
        orderRepository.save(order);

        //保存咖啡
        if (coffeeOrderDTO.getItems() != null && coffeeOrderDTO.getItems().size() != 0) {
            List<CoffeeDTO> list = coffeeOrderDTO.getItems();
            for (CoffeeDTO coffeeDTO : list) {
                CoffeeDO coffeeDO = (CoffeeDO) DO2DTOUtil.getInstance().dtoToDo(coffeeDTO, CoffeeDO.class);
                coffeeDO.setOrder_id_fk(order.getId());
                coffeeDO.setPrice(Money.of(CurrencyUnit.of("CNY"), coffeeDTO.getPrice()));
                coffeeRepository.save(coffeeDO);
            }
        }
    }

    /**
     * 获取一个订单信息
     */
    public CoffeeOrderDTO getOrderById(Long id) {
        //获取订单主体
        CoffeeOrderDO orderDO = orderRepository.queryOrderWithId(id);
        CoffeeOrderDTO orderDTO = (CoffeeOrderDTO) DO2DTOUtil.getInstance().doToDto(orderDO, CoffeeOrderDTO.class);

        //获取订单下的咖啡列表
        List<CoffeeDO> coffeeList = coffeeRepository.selectCoffeeListWithOrder(id);
        if (coffeeList != null && coffeeList.size() != 0) {
            List<CoffeeDTO> coffeeDTOList = new ArrayList<>();
            for (CoffeeDO coffeeDo : coffeeList) {
                CoffeeDTO dto = new CoffeeDTO(coffeeDo);
                coffeeDTOList.add(dto);
            }
            orderDTO.setItems(coffeeDTOList);
        }
        return orderDTO;
    }

    /**
     * 修改订单状态
     */
    public boolean updateState(CoffeeOrderDO order, OrderState state) {
        if (state.compareTo(order.getState()) <= 0) {
            log.warn("Wrong state order{},{}", state, order.getState());
            return false;
        }
        order.setState(state);
        orderRepository.save(order);
        log.info("update Order{}", order);
        return false;
    }

    /**
     * 根据客户id，获取订单列表
     */
    public List<CoffeeOrderDTO> getOrderListByCustomerId(String customerId) {
        if (customerId.isEmpty()) return null;
        List<CoffeeOrderDTO> orderDTOList = new ArrayList<>();

        List<CoffeeOrderDO> orderDOList = orderRepository.queryOrderListWithCustomerId(customerId);
        for (CoffeeOrderDO orderDO : orderDOList) {
            CoffeeOrderDTO orderDTO = (CoffeeOrderDTO) DO2DTOUtil.getInstance().doToDto(orderDO, CoffeeOrderDTO.class);

            //获取订单下的咖啡列表
            List<CoffeeDO> coffeeList = coffeeRepository.selectCoffeeListWithOrder(orderDO.getId());
            if (coffeeList != null && coffeeList.size() != 0) {
                List<CoffeeDTO> coffeeDTOList = new ArrayList<>();
                for (CoffeeDO coffeeDo : coffeeList) {
                    CoffeeDTO dto = new CoffeeDTO(coffeeDo);
                    coffeeDTOList.add(dto);
                }
                orderDTO.setItems(coffeeDTOList);
            }
            orderDTOList.add(orderDTO);
        }

        return orderDTOList;
    }
}
