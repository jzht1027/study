package com.api.core.listener;

import com.api.core.config.GlobalConfig;
import com.api.core.config.TransConfig;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;
import com.api.common.util.*;

import java.lang.annotation.Annotation;
import java.util.Map;
import java.util.Set;

import static org.springframework.core.annotation.AnnotationUtils.*;

/**
 * @ClassName apiHttpListener
 * @Description 使用 ApplicationListener 来初始化一些数据到 application 域中的监听器
 * @Author
 * @Date 2021/4/9 10:54
 * @Version 1.0
 **/
@Component
public class apiHttpListener implements ApplicationListener<ContextRefreshedEvent> {
    @Override
    public void onApplicationEvent(ContextRefreshedEvent event) {
        // 先获取到 application 上下文
        ApplicationContext applicationContext = event.getApplicationContext();
        ApplicationContextUtils.setApplicationContext(applicationContext);
        Class<? extends Annotation> annotations = TransService.class;
        Map<String,Object> beanWithAnnotations = applicationContext.getBeansWithAnnotation(annotations);

        Set<Map.Entry<String, Object>> entrySet=  beanWithAnnotations.entrySet();

        for(Map.Entry<String, Object> map : entrySet){
            Class<? extends Object>  clazz = map.getValue().getClass();// 获取ben
            TransService transService = findAnnotation(clazz,TransService.class);

            System.out.println("init:"+transService.transCode()+","+transService.transName());

            TransConfig transConfig = new TransConfig();
            transConfig.setTransCode(transService.transCode());
            transConfig.setTransName(transService.transName());
            transConfig.setReqDto(transService.ReqDto());
            transConfig.setRspDto(transService.RspDto());
            transConfig.setTransCls(clazz);

            GlobalConfig.load(transConfig);
        }
    }
}
