package com.api.common.util;

public enum RespCode {
    SUCCESS(0, "SUCCESS"),
    WARN(-1, "网络异常，请稍后重试");

    public int code;
    public String msg;

    RespCode(int code, String msg) {
        this.msg = msg;
    }

    public int getCode() {
        return code;
    }
    public String getMsg() {
        return msg;
    }
}
