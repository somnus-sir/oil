package com.whn.hellospring.controller;

import com.whn.hellospring.common.StateMessage;
import com.whn.hellospring.common.Status;
import com.whn.hellospring.model.VersionDO;
import com.whn.hellospring.request.VersionRequest;
import com.whn.hellospring.service.AppService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(value = "/app")
public class AppController {

    @Autowired
    AppService appService;

    /**
     * 获取版本号
     */
    @GetMapping(value = "/version")
    public Status getVersion(@RequestBody VersionRequest request) {
        try {
            List<VersionDO> version = appService.getVersion(request.getEquipment());
            Status status = new Status(StateMessage.SUCCESS, version);
            return status;
        } catch (Exception e) {
            return new Status(StateMessage.UN_KNOW_REASON);
        }
    }
}