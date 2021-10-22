package com.api.modules.trans;

import com.api.core.inter.Acction;
import com.api.common.util.*;
import com.api.core.inter.TransBaseReqDto;
import com.api.core.inter.TransRspDto;
import com.api.modules.dto.PaymentReq;
import com.api.modules.dto.PaymentRsp;
import com.api.modules.service.PaymentService;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * @ClassName HelloController
 * @Description
 * @Author
 * @Date 2021/4/7 9:26
 * @Version 1.0
 **/
@TransService(transCode="Payment",transName="PAY",ReqDto= PaymentReq.class, RspDto= PaymentRsp.class)
public class Payment extends Acction {

    @Autowired
    PaymentService paymentService;

    PaymentRsp paymentRsp;

    @Override
    public TransRspDto execute(TransBaseReqDto reqDto) throws Exception {
        PaymentReq paymentReq = (PaymentReq)reqDto;
        System.out.println("paymentReq:"+paymentReq.getTransCode());
        String password = paymentReq.getPassword();
        System.out.println("Password:"+password);
//        PaymentService paymentService = new PaymentService();
        String info = paymentService.select(password);
        System.out.println("@@@@@@@@@@@@@@:"+info);

        return null;
    }

    @Override
    public TransRspDto require (TransBaseReqDto reqDto){
//        paymentRsp.setPassword(info);
        System.out.println("Counterpartyid:"+paymentRsp.getPassword());

        return null;
    };

}