package com.whn.hellospring.repository;

import com.whn.hellospring.model.MenuCoffeeDO;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

/**
 * 菜单咖啡
 */
public interface MenuCoffeeRepository extends JpaRepository<MenuCoffeeDO,Long> {

    /**
     * 获取一个类型下的咖啡列表
     * @return
     */
    @Override
    Optional<MenuCoffeeDO> findById(Long aLong);
}
