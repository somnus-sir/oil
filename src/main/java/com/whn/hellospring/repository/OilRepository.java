package com.whn.hellospring.repository;

import com.whn.hellospring.model.OilDO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OilRepository extends JpaRepository<OilDO,Long> {

    /**
     * 分页查询所有
     *
     * @param spec
     * @param pageable
     * @return
     */
    Page<OilDO> findAll(Specification spec, Pageable pageable);

    @Query(value = "select * from t_oil where customer_id = ?1 ORDER BY time Desc ",nativeQuery = true)
    List<OilDO> finAllSortByTime(Long customer_id);


    @Query(value = "select sum(num) from t_oil where status=1 && customer_id = ?1",nativeQuery = true)
    double countNum(Long customer_id);

    @Query(value = "select sum(interval_mileage) from t_oil where status=1 && customer_id = ?1",nativeQuery = true)
    double countIntervalMileage(Long customer_id);

    @Query(value = "select interval_oil_wear from t_oil where status=1 AND customer_id=?1 ORDER BY time DESC LIMIT 10",nativeQuery = true)
    List<Double> findWearList(Long customer_id);


    @Query(value = "SELECT fuel_one_day FROM t_oil WHERE status=1 AND customer_id=?1 ORDER BY time DESC LIMIT 10", nativeQuery = true)
    List<Double> findFuelOneDay(Long customer_id);

}
