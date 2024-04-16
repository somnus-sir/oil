package com.whn.hellospring.repository;

import com.whn.hellospring.model.CoffeeDO;
import org.apache.ibatis.annotations.Delete;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * 咖啡
 */
public interface CoffeeRepository extends JpaRepository<CoffeeDO, Long> {

    /**
     * 获取一个订单下的所有咖啡
     */
    @Query(value = "SELECT * FROM t_coffee where order_id_fk=?1", nativeQuery = true)
    List<CoffeeDO> selectCoffeeListWithOrder(Long order_id_fk);


    /**
     * 删除一个订单下的咖啡
     */
    @Transactional //事务
    @Modifying  //更新
    @Query(value = "delete FROM T_COFFEE where order_id_fk=?1", nativeQuery = true)
    int deleteCoffeeByOrderId(Long order_id_fk);


}
