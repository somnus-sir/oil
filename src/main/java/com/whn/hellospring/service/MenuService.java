package com.whn.hellospring.service;

import com.whn.hellospring.model.MenuDO;
import com.whn.hellospring.repository.MenuCoffeeRepository;
import com.whn.hellospring.repository.MenuRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
public class MenuService {

    @Autowired
    private MenuRepository menuRepository;

    @Autowired
    private MenuCoffeeRepository menuCoffeeRepository;

    /**
     * 获取菜单类型列表
     */
    public List<MenuDO> getMenuTypeList() {
         return menuRepository.getMenuList();

//        List<MenuDTO> returnList = new ArrayList<>();
//        for (MenuDO menudo : menuRepository.getMenuList()) {
//            Optional<MenuCoffeeDO> coffeeList = menuCoffeeRepository.findById(menudo.getId());
//
//            MenuDTO menuDTO = new MenuDTO();
//            if (coffeeList.isPresent()) menuDTO.setCoffee_list(MyOptional.toList(coffeeList));
//            menuDTO.setId(menudo.getId());
//            menuDTO.setCreateTime(menudo.getCreateTime());
//            menuDTO.setUpdateTime(menudo.getUpdateTime());
//            menuDTO.setType(menudo.getType());
//
//            returnList.add(menuDTO);
//        }
//        return returnList;

    }
}
