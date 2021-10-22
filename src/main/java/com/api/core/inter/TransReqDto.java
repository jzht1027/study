package com.api.core.inter;


import com.alibaba.fastjson.JSONObject;

/**
 * @ClassName TransReqDto
 * @Description
 * @Author
 * @Date 2021/4/12 15:02
 * @Version 1.0
 **/
public interface TransReqDto {

    public int init(JSONObject para) throws Exception;
}
