package com.atguigu.factory;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanPostProcessor;

public class MyBeanPostProcessor implements BeanPostProcessor {

	public Object postProcessBeforeInitialization(Object bean, String beanName) throws BeansException {
		System.out.println("["+beanName+"]  postProcessBeforeInitialization 被创建");
		return bean;
	}

	public Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException {
		System.out.println("["+beanName+"]  postProcessAfterInitialization 被创建");
		return bean;
	}

	

}
