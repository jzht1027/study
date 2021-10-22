package com.api.core.config;

/**
 * @ClassName TransConfig
 * @Description
 * @Author
 * @Date 2021/4/9 14:39
 * @Version 1.0
 **/
public class TransConfig {

    private String TransCode;

    private String TransName;

    private Class ReqDto;

    private Class RspDto;

    private Class TransCls;

    public String getTransCode() {
        return TransCode;
    }

    public void setTransCode(String transCode) {
        TransCode = transCode;
    }

    public String getTransName() {
        return TransName;
    }

    public void setTransName(String transName) {
        TransName = transName;
    }

    public Class getReqDto() {
        return ReqDto;
    }

    public void setReqDto(Class reqDto) {
        ReqDto = reqDto;
    }

    public Class getRspDto() {
        return RspDto;
    }

    public void setRspDto(Class rspDto) {
        RspDto = rspDto;
    }

    public Class getTransCls() {
        return TransCls;
    }

    public void setTransCls(Class transCls) {
        TransCls = transCls;
    }
}
