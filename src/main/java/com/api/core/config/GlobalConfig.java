package com.api.core.config;

import java.util.HashMap;

/**
 * @ClassName GlobalConfig
 * @Description
 * @Author
 * @Date 2021/4/9 14:53
 * @Version 1.0
 **/
public class GlobalConfig {
    private static HashMap<String,TransConfig> trans = new HashMap<>();


    public static void load(TransConfig transConfig){
        trans.put(transConfig.getTransCode(),transConfig);
    }

    public static TransConfig getTransClass(String TransCode){
        return trans.get(TransCode);
    }

    public static Class getTransReqDto(String TransCode){
        return trans.get(TransCode).getReqDto();
    }
}
