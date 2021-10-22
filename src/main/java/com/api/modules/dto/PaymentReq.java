package com.api.modules.dto;

import com.alibaba.fastjson.JSONObject;
import com.api.core.inter.TransBaseReqDto;
/**
 * @ClassName PaymentReq
 * @Description
 * @Author
 * @Date 2021/4/12 17:31
 * @Version 1.0
 **/
public class PaymentReq implements TransBaseReqDto {

    private String transCode;
    private String name;
    private String password;

    @Override
    public int init(JSONObject para) throws Exception {
        transCode = para.getString("transCode");
        name = para.getString("name");
        password = para.getString("password");

        return 0;
    }

    public String getTransCode() {
        return transCode;
    }

    public void setTransCode(String transCode) {
        this.transCode = transCode;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
