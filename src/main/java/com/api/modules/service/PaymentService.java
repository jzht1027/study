package com.api.modules.service;

import com.api.modules.dao.edsCounterPartyMapper;
import com.api.modules.model.edsCounterParty;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * @ClassName PaymentService
 * @Description
 * @Author
 * @Date 2021/5/17 16:13
 * @Version 1.0
 **/
@Service
public class PaymentService {

//    @Resource
    @Autowired
    edsCounterPartyMapper eds_counterpartyMapper;

    public String select(String counterpartyid){
        System.out.println("Password1:"+counterpartyid);
        edsCounterParty eds_counterparty = eds_counterpartyMapper.selectByPrimaryKey(counterpartyid);
        return eds_counterparty.getCounterpartyid();
    }

}
