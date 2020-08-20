package com.pengfu.util;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;

/**
 * Spring容器操作
 * @author PrideZH
 */
@Component
@SuppressWarnings("all")
public class SpringContextUtils implements ApplicationContextAware {

    /** spring核心容器 */
    private static ApplicationContext applicationContext;

    /** 设置核心容器 */
    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        this.applicationContext = applicationContext;
    }

    /** 获取核心容器 */
    public static ApplicationContext getApplicationContext() {
        return applicationContext;
    }

    /** 通过name获取 Bean */
    public static Object getBean(String name) {
        return getApplicationContext().getBean(name);
    }

    /** 通过class获取Bean */
    public static <T> T getBean(Class<T> clazz) {
        return getApplicationContext().getBean(clazz);
    }

    /** 通过name,以及Clazz返回指定的Bean */
    public static <T> T getBean(String name, Class<T> clazz) {
        return getApplicationContext().getBean(name, clazz);
    }
}