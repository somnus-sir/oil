package com.whn.hellospring.service;

import com.whn.hellospring.model.VersionDO;
import com.whn.hellospring.repository.AppRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AppService {

    @Autowired
    AppRepository repository;

    /**
     * 获取版本列表
     */
    public List<VersionDO> getVersion(int equipment) {
        List<VersionDO> versionDOList = repository.findByEquipment(equipment);
        return versionDOList;
    }


}
