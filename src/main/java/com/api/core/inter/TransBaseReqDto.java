package com.api.core.inter;

import com.alibaba.fastjson.JSONObject;

/**
 * @ClassName TransBaseReqDto
 * @Description
 * @Author
 * @Date 2021/4/12 15:06
 * @Version 1.0
 **/
public interface TransBaseReqDto {

    public int init(JSONObject para) throws Exception;
}
