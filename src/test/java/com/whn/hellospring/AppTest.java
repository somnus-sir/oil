package com.whn.hellospring;


import com.whn.hellospring.model.VersionDO;
import com.whn.hellospring.service.AppService;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest
@Slf4j
public class AppTest {

    @Autowired
    AppService service;

    @Test
    public void getVersion(){
        List<VersionDO> version = service.getVersion(1);
        log.info("version{}:"+version);
    }
}
