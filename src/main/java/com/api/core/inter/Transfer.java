package com.api.core.inter;

/**
 * @ClassName Transfer
 * @Description
 * @Author
 * @Date 2021/4/12 15:18
 * @Version 1.0
 **/
public interface Transfer {

    public TransRspDto execute(TransBaseReqDto reqDto) throws Exception;
}
