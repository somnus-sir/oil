package com.whn.hellospring.repository;

import com.whn.hellospring.model.CoffeeOrderDO;
import org.apache.ibatis.annotations.Delete;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * 订单数据操作
 */
public interface CoffeeOrderRepository extends JpaRepository<CoffeeOrderDO, Long> {

//    //根据咖啡名，获取订单
//    List<CoffeeOrderDO> findByItems_Name(String name);

    //根据订单id获取订单
    @Query(value = "select * from T_ORDER where id=?1",nativeQuery = true)
    CoffeeOrderDO queryOrderWithId(Long id);

    //根据订单id删除订单
    @Transactional //事务
    @Modifying  //更新
    @Query(value = "DELETE from T_ORDER where id=?1",nativeQuery = true)
    int deleteOrderWithId(Long id);

    //根据客户，获取订单
    @Query(value = "select * from t_order where customer_id = ?1", nativeQuery = true)
    List<CoffeeOrderDO> queryOrderListWithCustomerId(String customerId);

}
