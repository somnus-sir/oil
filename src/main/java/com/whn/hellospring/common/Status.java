package com.whn.hellospring.common;

import java.util.HashMap;
import java.util.Map;

public class Status {
    private boolean state;
    private String errorCode;
    private String message;
    private Map<String, Object> data;

    public Status(StateMessage stateMessage) {
        this.state = stateMessage.getState();
        this.errorCode = stateMessage.getErrorCode();
        this.message = stateMessage.getMessage();
        data = new HashMap<>();
    }
    public static Status result(StateMessage stateMessage,Map<String, Object> dataMap){
        Status status =  new Status(stateMessage);
        status.setData(dataMap);
        return status;
    }
    /**
     * 返回客户端数据类型
     *
     * @param stateMessage 返回客户端消息
     * @param value        返回客户端值
     */
    public Status(StateMessage stateMessage, Object value) {
        this.state = stateMessage.getState();
        this.errorCode = stateMessage.getErrorCode();
        this.message = stateMessage.getMessage();
        data = new HashMap<>();
        data.put("result", value);
    }



    /**
     * 返回客户端数据类型
     *
     * @param stateMessage 返回客户端消息
     * @param map          返回客户端数据集合
     */
    public Status(StateMessage stateMessage, Map<String, Object> map) {
        this.state = stateMessage.getState();
        this.errorCode = stateMessage.getErrorCode();
        this.message = stateMessage.getMessage();
        data = new HashMap<>();
        data.putAll(map);
    }

    public boolean getState() {
        return state;
    }

    public void setState(boolean state) {
        this.state = state;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Object getData() {
        return data;
    }

    public void setData(Map<String, Object> dataMap) {
        this.data = dataMap;
    }

    public void setData(String key, Object value) {
        this.data.put(key, value);
    }

    @Override
    public String toString() {
        return "Status [state=" + state + ", message=" + message + ", data=" + data + "]";
    }

    public String getErrorCode() {
        return errorCode;
    }

    public void setErrorCode(String errorCode) {
        this.errorCode = errorCode;
    }
}
