package com.mia.chatclient.base.constant;

public enum MsgCode {
    SUCCESSFUL(100,"请求成功"),
    SYSTEM_ERROR(500,"系统繁忙，请稍后再试");
    private int msgCode;
    private String message;
    MsgCode(int msgCode, String message){
        this.msgCode=msgCode;
        this.message=message;
    }
    public int getMsgCode() {
        return msgCode;
    }

    public String getMessage() {
        return message;
    }
}
