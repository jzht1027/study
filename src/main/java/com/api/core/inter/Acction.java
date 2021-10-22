package com.api.core.inter;

import com.api.modules.dto.*;

/**
 * @ClassName Acction
 * @Description
 * @Author
 * @Date 2021/4/8 17:38
 * @Version 1.0
 **/
public class Acction implements Transfer{

    public void inToInfo(TransBaseReqDto reqDto){

    };

    public void serviceLogic(){

    };

    public TransRspDto require (TransBaseReqDto reqDto){
        return null;
    };

    @Override
    public TransRspDto execute(TransBaseReqDto reqDto) throws Exception {
        inToInfo(reqDto);
        return require(reqDto);
    }

}
