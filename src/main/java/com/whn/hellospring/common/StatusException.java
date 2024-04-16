package com.whn.hellospring.common;


/**
 * 功能描述：异常类
 */
public class StatusException extends Exception {
    public StateMessage stateMessage;

    public StatusException(StateMessage stateMessage) {
        super();
        this.stateMessage = stateMessage;
    }

    public StateMessage getStateMessage() {
        return stateMessage;
    }

    public void setStateMessage(StateMessage stateMessage) {
        this.stateMessage = stateMessage;
    }

}
