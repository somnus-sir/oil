package com.whn.hellospring.controller;

import com.whn.hellospring.common.StateMessage;
import com.whn.hellospring.common.Status;
import com.whn.hellospring.model.OilDO;
import com.whn.hellospring.model.OilPriceBean;
import com.whn.hellospring.request.DeleteOilRecordRequest;
import com.whn.hellospring.request.OilRecordListRequest;
import com.whn.hellospring.service.OilService;
import com.whn.hellospring.utils.DateUtils;
import com.whn.hellospring.utils.StringUtil;
import lombok.extern.slf4j.Slf4j;
import org.hibernate.annotations.common.util.impl.Log;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import javax.validation.Valid;
import java.util.Collections;
import java.util.List;

@Slf4j
@RestController
@RequestMapping(value = "/oil")
public class OilController {

    @Autowired
    OilService service;



    /**
     * 市场油价
     */
    @PostMapping(value = "/price")
    public Status requestOilPrice(){
        try {
            OilPriceBean oilPriceBean = service.requestOilList();
            Status status = new Status(StateMessage.SUCCESS, "success");
            status.setData("list", oilPriceBean.getResult());
            return status;
        } catch (Exception e) {
            return new Status(StateMessage.UN_KNOW_REASON);
        }
    }


    /**
     * 平均油耗
     */
    @PostMapping(value = "/wear")
    public Status getOilWear(@RequestBody OilRecordListRequest request) {
        try {
            //平均油耗  = 加油总数量（num 数量相加 除第一次） / 区间里程总数（intervalmileage  相加  除第一次）* 100
            double numTotal = service.getNumTotal(request.getCustomer_id());
            double intervalMileageTotal = service.getIntervalMileageTotal(request.getCustomer_id());
            double oilWear = numTotal * 100.0 / intervalMileageTotal;
            String s = StringUtil.doubleToString1(oilWear);
            Status status = new Status(StateMessage.SUCCESS, "success");
            status.setData("wear", s);
            return status;
        } catch (Exception e) {
            return new Status(StateMessage.UN_KNOW_REASON);
        }
    }


    /**
     * 删除记录
     */
    @PostMapping(value = "/delete")
    public Status deleteCoffeeById(@RequestBody DeleteOilRecordRequest request) {
        try {
            OilDO oilDO = service.deleteRecord(request.getId());
            Status status = new Status(StateMessage.SUCCESS, "success");
            return status;
        } catch (Exception e) {
            return new Status(StateMessage.UN_KNOW_REASON);
        }
    }

    /**
     * 获取油耗列表
     */
    @PostMapping(value = "/wearList")
    public Status getWearList(@RequestBody OilRecordListRequest request) {
        try {
            List<Double> wearList = service.getWearList(request.getCustomer_id());
            //删除第一条记录，一般为空
            wearList.remove(0);
            Collections.reverse(wearList);
            Status status = new Status(StateMessage.SUCCESS, "success");
            status.setData("list", wearList);
            return status;
        } catch (Exception e) {
            return new Status(StateMessage.UN_KNOW_REASON);
        }
    }


    /**
     * 获取一天的油费
     */
    @PostMapping(value = "/fuelOneDayList")
    public Status getFuelOneDayList(@RequestBody OilRecordListRequest request) {
        try {
            List<Double> list = service.getFuelOneDay(request.getCustomer_id());
            //删除第一条记录，一般为空
            list.remove(0);
            Collections.reverse(list);
            Status status = new Status(StateMessage.SUCCESS, "success");
            status.setData("list", list);
            return status;
        } catch (Exception e) {
            return new Status(StateMessage.UN_KNOW_REASON);
        }
    }

    /**
     * 获取记录列表
     */
    @PostMapping(value = "/recordList")
    public Status getRecordList(@RequestBody OilRecordListRequest request) {
        try {
            List<OilDO> recordList = service.getRecordList(request.getCustomer_id());
            Status status = new Status(StateMessage.SUCCESS, "success");
            status.setData("list", recordList);
            return status;
        } catch (Exception e) {
            return new Status(StateMessage.UN_KNOW_REASON);
        }
    }

    @PostMapping(value = "/add_batch")
    public Status createRecordBatch(@Valid @RequestBody List<OilDO> list) {
        for(int i = list.size()-1; i>-1;i--){
            Status s = createRecord(list.get(i));
            System.out.println("add:"+s);
        }

        Status status = new Status(StateMessage.SUCCESS, "success");
        return status;
    }


    /**
     * 创建记录
     */
    @PostMapping(value = "/add")
    public Status createRecord(@Valid @RequestBody OilDO oilDO) {
        try {
            //上条有效记录的currentMileage
            OilDO recentlyRecord = service.getRecentlyRecord(oilDO.getCustomer_id());
            int recentlyMileage = 0;
            if (recentlyRecord != null) {
                recentlyMileage = recentlyRecord.getMileage();
                //区间里程intervalmileage  = currentMileage - 上条有效记录的currentMileage
                int intervalMileage = oilDO.getMileage() - recentlyMileage;
                oilDO.setIntervalMileage(intervalMileage);

                //计算区间油耗  intervalOilWear  = num / intervalmileage
                double intervalOilWear = oilDO.getNum() * 100.0 / intervalMileage;
                oilDO.setIntervalOilWear(intervalOilWear);

                //计算区间每天油费   fuelOneDay = price /  (oilDO.Date - recentlyRecord.Date)
                long i = DateUtils.daysBetween(recentlyRecord.getTime(), oilDO.getTime());
                log.info("createRecord","间隔天数:"+i);
                oilDO.setBetweenDay(i);
                double fuelOneDay = oilDO.getPrice() / i;
                oilDO.setFuelOneDay(fuelOneDay);

            }else{
                oilDO.setIntervalOilWear(0.0);
            }

            oilDO.setStatus(1);
            oilDO.setTime(oilDO.getTime());
            OilDO oil = service.addUpdateRecord(oilDO);
            Status status = new Status(StateMessage.SUCCESS, "success");
            status.setData("bean", oil);
            return status;
        } catch (Exception e) {
            return new Status(StateMessage.UN_KNOW_REASON);
        }
    }


//    /**
//     * 更新记录
//     */
//    @PostMapping(value = "/update")
//    public Status updateRecord(@Valid @RequestBody OilDO oilDO){
//        try {
//            oilDO.setTime(oilDO.getTime());
//            OilDO oil = service.addUpdateRecord(oilDO);
//            Status status = new Status(StateMessage.SUCCESS, "success");
//            status.setData("bean",oil);
//            return status;
//        }catch (Exception e){
//            return new Status(StateMessage.UN_KNOW_REASON);
//        }
//    }
}
