package com.api.modules.dto;

import com.alibaba.fastjson.JSONObject;
import com.api.core.inter.TransBaseReqDto;
import com.api.core.inter.TransRspDto;

/**
 * @ClassName PaymentRsp
 * @Description
 * @Author
 * @Date 2021/4/12 17:32
 * @Version 1.0
 **/
public class PaymentRsp implements TransRspDto {

    private String Password;

    @Override
    public String doReturn() {
        return Password;
    }

    public String getPassword() {
        return Password;
    }

    public void setPassword(String password) {
        Password = password;
    }
}
