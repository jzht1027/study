package com.api.common.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationContext;

/**
 * @ClassName ApplicationContextUtils
 * @Description
 * @Author
 * @Date 2021/4/9 11:35
 * @Version 1.0
 **/
public class ApplicationContextUtils {
    private static Logger log = LoggerFactory.getLogger(ApplicationContextUtils.class);

    private static ApplicationContext applicationContext = null;

    public static void setApplicationContext(ApplicationContext app){
        applicationContext = app;
    }

    /**
     * 获取 applicationContext
     * @return
     */
    public static ApplicationContext getApplicationContext() {
        return applicationContext;
    }

    /**
     * 通过name获取Bean.
     * @param name
     * @return
     */
    public static Object getBean(String name) {
        return getApplicationContext().getBean(name);
    }

    /**
     * 通过class获取Bean.
     * @param clazz
     * @param <T>
     * @return
     */
    public static <T> T getBean(Class<T> clazz) {
        return getApplicationContext().getBean(clazz);
    }

    /**
     * 通过name,Clazz获取Bean
     * @param name
     * @param clazz
     * @param <T>
     * @return
     */
    public static <T> T getBean(String name, Class<T> clazz) {
        return getApplicationContext().getBean(name, clazz);
    }

}
