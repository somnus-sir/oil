package com.whn.hellospring;


import com.whn.hellospring.service.MenuService;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
@Slf4j
public class MenuTest {

    @Autowired
    MenuService service;

    @Test
    public void getTypeList(){
//        List<MenuDTO> menuList = service.getMenuTypeList();
//        log.info("menuList{}:"+menuList);
    }

    @Test
    public void betweenDay(){

    }



}
