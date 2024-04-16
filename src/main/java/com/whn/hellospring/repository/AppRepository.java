package com.whn.hellospring.repository;

import com.whn.hellospring.model.VersionDO;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface AppRepository extends BaseRepository<VersionDO,Long> {

    List<VersionDO> findByEquipment(int equipment);

}
