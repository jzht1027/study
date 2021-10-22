package com.api.common.util;

import org.springframework.stereotype.Component;

import java.lang.annotation.*;

/**
 * @ClassName TransService
 * @Description
 * @Author
 * @Date 2021/4/9 13:41
 * @Version 1.0
 **/
@Target(ElementType.TYPE)
@Retention(value = RetentionPolicy.RUNTIME)
@Documented
@Inherited
@Component
public @interface TransService {
    String transCode();

    String transName();

    Class ReqDto();

    Class RspDto();

}
