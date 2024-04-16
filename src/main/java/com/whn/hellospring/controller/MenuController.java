package com.whn.hellospring.controller;

import com.whn.hellospring.common.StateMessage;
import com.whn.hellospring.common.Status;
import com.whn.hellospring.model.MenuDO;
import com.whn.hellospring.service.MenuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(value = "/menu")
public class MenuController {

    @Autowired
    MenuService service;

    /**
     * 获取菜单
     */
    @GetMapping(value = "/get")
    public Status getMenuTypeList(){
        try{
            List<MenuDO> menuTypeList = service.getMenuTypeList();
            Status status = new Status(StateMessage.SUCCESS, menuTypeList);
            return status;
        } catch (Exception e) {
            return new Status(StateMessage.UN_KNOW_REASON);
        }
    }
}
