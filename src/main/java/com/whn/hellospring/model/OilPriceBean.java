package com.whn.hellospring.model;

import java.util.List;
import java.util.Map;

public class OilPriceBean {
    List<Map<String,String>> result;

    public List<Map<String, String>> getResult() {
        return result;
    }

    public void setResult(List<Map<String, String>> result) {
        this.result = result;
    }

    @Override
    public String toString() {
        return "OilPriceBean{" +
                "result=" + result +
                '}';
    }
}
