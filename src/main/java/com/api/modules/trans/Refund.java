package com.api.modules.trans;

import com.api.core.inter.Acction;
import com.api.common.util.*;
import com.api.modules.dto.PaymentReq;
import com.api.modules.dto.PaymentRsp;

/**
 * @ClassName HelloControllerTwo
 * @Description
 * @Author
 * @Date 2021/4/8 15:26
 * @Version 1.0
 **/
@TransService(transCode="Refund",transName="Ref",ReqDto= PaymentReq.class, RspDto= PaymentRsp.class)
public class Refund extends Acction {

}
