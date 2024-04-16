package com.whn.hellospring.repository;

import com.whn.hellospring.model.CoffeeOrderDO;
import com.whn.hellospring.model.MenuDO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

/**
 * 菜单类型
 */
public interface MenuRepository extends JpaRepository<MenuDO, Long> {

    /**
     * 获取菜单的类型列表
     */
    @Query(value = "select * from t_menu",nativeQuery = true)
    List<MenuDO> getMenuList();



}
