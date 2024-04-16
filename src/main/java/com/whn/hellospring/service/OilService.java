package com.whn.hellospring.service;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.whn.hellospring.model.OilDO;
import com.whn.hellospring.model.OilPriceBean;
import com.whn.hellospring.repository.OilRepository;
import com.whn.hellospring.utils.HttpUtils;
import lombok.extern.slf4j.Slf4j;
import org.apache.http.HttpResponse;
import org.apache.http.util.EntityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Slf4j
@Service
public class OilService {


    @Autowired
    private OilRepository oilRepository;


    /**
     * 获取三方油价列表
     * @return
     */
    public OilPriceBean requestOilList() throws Exception {
        String host = "http://apis.juhe.cn";
        String path = "/gnyj/query";
        String key = "212b6526c4f8a3899ca406a1173bafa8";

        JSONObject requestObj = new JSONObject();
//        requestObj.put("key", key);
        String bodys = requestObj.toJSONString();


        JSONObject resObj = new JSONObject();
//        resObj.put("key",key);
        try {


            Map<String, String> headers = new HashMap<String, String>();
            Map<String, String> querys = new HashMap<String, String>();
            querys.put("key",key);

            HttpResponse response = HttpUtils.doPost(host, path, "POST", headers, querys, bodys);
            int stat = response.getStatusLine().getStatusCode();
            if (stat != 200) {
                System.out.println("Http code: " + stat);
                System.out.println("http header error msg: " + response.getFirstHeader("X-Ca-Error-Message"));
                String bodyErr = EntityUtils.toString(response.getEntity());
                System.out.println("Http body error msg:" + bodyErr);
                resObj.put("errorMsg",bodyErr);
            }else{
                String res = EntityUtils.toString(response.getEntity());
                resObj.put("status","SUCCESS");
                resObj.putAll(JSON.parseObject(res));
            }
        } catch (Exception e) {
            resObj.put("status","FAIL");
            e.printStackTrace();
        } finally {
            if(!"SUCCESS".equals(resObj.getString("status"))){
                throw new Exception();
            }
            log.info("resObj={}",JSONObject.toJSONString(resObj,true));
            OilPriceBean oilPriceBean = JSONObject.parseObject(String.valueOf(resObj), OilPriceBean.class);
            log.info("bean"+oilPriceBean.toString());

            return oilPriceBean;
        }
    }







    //获取id，里程，区间里程
    //计算区间里程
    //根据id，将区间里程赋值到数据库

    /**
     * test
     * 计算区间里程
     */
    public void calculationIntervalMileage(Long customer_id) {
        List<OilDO> list = oilRepository.finAllSortByTime(customer_id);
        log.info("list:{}", list);
        for (int i = 1; i < list.size(); i++) {
            list.get(i).setMileage(list.get(i-1).getMileage()-list.get(i).getIntervalMileage());
        }
    }


    /**
     * 油耗列表
     */
    public List<Double> getWearList(Long customer_id) {
        return oilRepository.findWearList(customer_id);
    }


    /**
     * 一天油费
     */
    public List<Double> getFuelOneDay(Long customer_id) {
        return oilRepository.findFuelOneDay(customer_id);
    }



    /**
     * 获取有效数量num之和
     *
     * @return
     */
    public double getNumTotal(Long customer_id) {
        return oilRepository.countNum(customer_id);
    }

    /**
     * 获取有效区间里程和
     *
     * @return
     */
    public double getIntervalMileageTotal(Long customer_id) {
        return oilRepository.countIntervalMileage(customer_id);
    }


    /**
     * 获取最新的一条有效记录
     */
    public OilDO getRecentlyRecord(Long customer_id) {
        List<OilDO> recordList = oilRepository.finAllSortByTime(customer_id);
        if (recordList == null || recordList.size() == 0) return null;
        int index = -1;
        for (int i = 0; i < recordList.size(); i++) {
            OilDO oilDO = recordList.get(i);
            if (oilDO.getStatus() == 1) {
                index = i;
                break;
            }
        }
        if (index == -1) {
            return null;
        } else {
            return recordList.get(index);
        }
    }


    /**
     * 获取有效记录列表
     */
    public List<OilDO> getRecordList(Long customer_id) {
       log.info("customer_id:{}", customer_id);
        List<OilDO> recordList = oilRepository.finAllSortByTime(customer_id);
        log.info("recordList:{}", recordList);
//        Collections.reverse(recordList);
        List<OilDO> list = new ArrayList<>();
        for (int i = 0; i < recordList.size(); i++) {
            if (recordList.get(i).getStatus() == 1) {
                list.add(recordList.get(i));
            }
        }
        log.info("list:{}", list);
        return list;
    }


    /**
     * 获取全部记录列表
     * 包括被删除的status = 0
     */
    public List<OilDO> getRecordAllList(Long customer_id) {
        List<OilDO> recordList = oilRepository.finAllSortByTime(customer_id);
//        Collections.reverse(recordList);
        log.info("recordList:{}", recordList);
        return recordList;
    }


    /**
     * 新增更新
     */
    public OilDO addUpdateRecord(OilDO oilDO) {
        OilDO save = oilRepository.save(oilDO);
        return save;
    }

    /**
     * 删除
     *
     * @return
     */
    public OilDO deleteRecord(Long id) {
        OilDO oilDO = oilRepository.getOne(id);
        oilDO.setStatus(0);
        return oilRepository.save(oilDO);
    }


}
