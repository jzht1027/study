package com.api.modules.trans;

import com.api.common.util.TransService;
import com.api.core.inter.Acction;
import com.api.core.inter.TransBaseReqDto;
import com.api.core.inter.TransRspDto;
import com.api.modules.dto.PaymentReq;
import com.api.modules.dto.PaymentRsp;
import com.api.common.util.RedisService;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * @ClassName SentinelTest
 * @Description
 * @Author
 * @Date 2021/8/24 16:15
 * @Version 1.0
 **/
@RunWith(SpringRunner.class)
@SpringBootTest
@TransService(transCode="SentinelTest",transName="SentinelTest",ReqDto= PaymentReq.class, RspDto= PaymentRsp.class)
public class SentinelTest extends Acction {
    @Autowired
    private RedisService service;

    @Override
    public TransRspDto execute(TransBaseReqDto reqDto) throws Exception {
        service.set("myname", "chhliu");
//        Student s = new Student();
//        s.setId("001");
//        s.setName("chhliu");
//        s.setGrade("һ�꼶");
//        s.setAge("28");
//        service.set(s);

        String name = service.get("myname");
        System.out.println("name:" + name);

//        Student stu = service.getStudent("001");
//        System.out.println(stu);

        return null;
    }

    @Override
    public TransRspDto require (TransBaseReqDto reqDto){
        return null;
    };
}
