package com.api.modules;

import com.alibaba.fastjson.JSONObject;
import com.api.common.util.ApplicationContextUtils;
import com.api.common.util.RespCode;
import com.api.common.util.jdbcUtil;
import com.api.core.config.GlobalConfig;
import com.api.core.config.TransConfig;
import com.api.core.inter.TransBaseReqDto;
import com.api.core.inter.TransReqDto;
import com.api.core.inter.TransRspDto;
import com.api.core.inter.Transfer;
import com.api.modules.dto.RespEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.io.BufferedReader;
import java.io.InputStreamReader;

/**
 * @ClassName ApiHttpAction
 * @Description
 * @Author
 * @Date 2021/4/9 15:04
 * @Version 1.0
 **/
@RestController
@RequestMapping("/api")
public class ApiHttpAcction {

    @PostMapping("/json")
    public RespEntity execute(@RequestBody JSONObject object) {
        System.out.println("RequestBody:"+object.toString());

        try {
            String transCod = object.getString("transCode");
            System.out.println("transCod:"+transCod);
            TransConfig transConfig= GlobalConfig.getTransClass(transCod);
            System.out.println("transConfig:"+transConfig.getReqDto());
            Class clazz = GlobalConfig.getTransReqDto(transCod);
            System.out.println("clazz:"+clazz.newInstance());
            TransBaseReqDto transReqDto = (TransBaseReqDto)clazz.newInstance();
            transReqDto.init(object);
            System.out.println("transReqDto:"+transReqDto.toString());
            if (transConfig == null){
                return new RespEntity(RespCode.WARN, object);
            }

            System.out.println("###:"+transConfig.getTransCls());

            Transfer transfer = (Transfer)ApplicationContextUtils.getBean(transConfig.getTransCls());
            TransRspDto transRspDto = transfer.execute(transReqDto);


        }catch (Exception e){
            e.printStackTrace();
        }

        return new RespEntity(RespCode.SUCCESS, object);
    }


    @PostMapping("/http")
    public RespEntity exece(HttpServletRequest request) {
        System.out.println("request:"+request);
        //获取到JSONObject
        JSONObject object = this.getJSONParam(request);
        System.out.println("object:"+object);
        try {
            String transCod = object.getString("transCode");
            System.out.println("transCod:"+transCod);
            TransConfig transConfig= GlobalConfig.getTransClass(transCod);
            System.out.println("transConfig:"+transConfig.getReqDto());
            Class clazz = GlobalConfig.getTransReqDto(transCod);
            System.out.println("clazz:"+clazz.newInstance());
            TransBaseReqDto transReqDto = (TransBaseReqDto)clazz.newInstance();
            transReqDto.init(object);
            System.out.println("transReqDto:"+transReqDto.toString());
            if (transConfig == null){
                return new RespEntity(RespCode.WARN, object);
            }

            System.out.println("###:"+transConfig.getTransCls());

            Transfer transfer = (Transfer)ApplicationContextUtils.getBean(transConfig.getTransCls());
            TransRspDto transRspDto = transfer.execute(transReqDto);
        }catch (Exception e){
            e.printStackTrace();
        }
        return new RespEntity(RespCode.SUCCESS, object);
    }


    /**
     * 创建日期:2018年4月6日<br/>
     * 代码创建:黄聪<br/>
     * 功能描述:通过request来获取到json数据<br/>
     * @param request
     * @return
     */
    public JSONObject getJSONParam(HttpServletRequest request){
        JSONObject jsonParam = null;
        try {
            // 获取输入流
            BufferedReader streamReader = new BufferedReader(new InputStreamReader(request.getInputStream(), "UTF-8"));

            // 写入数据到Stringbuilder
            StringBuilder sb = new StringBuilder();
            String line = null;
            while ((line = streamReader.readLine()) != null) {
                sb.append(line);
            }
            jsonParam = JSONObject.parseObject(sb.toString());
            // 直接将json信息打印出来
            System.out.println("直接将json信息打印出来"+jsonParam.toJSONString());
        } catch (Exception e) {
            e.printStackTrace();
        }
        return jsonParam;
    }

    @PostMapping("/jdbc")
    public boolean exece() {
        return jdbcUtil.getLoginJdbc();
    }
}
