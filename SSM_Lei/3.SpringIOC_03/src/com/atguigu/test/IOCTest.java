package com.atguigu.test;

import static org.junit.Assert.*;

import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.atguigu.servlet.Bookservlet;

public class IOCTest {
	
	private ApplicationContext ioc = new ClassPathXmlApplicationContext("applicationContext.xml");

	@Test
	public void test() {
		Object bean = ioc.getBean("bookDao");
		Object bean2 = ioc.getBean("bookDao");
		System.out.println(bean == bean2);
	}
	
	@Test
	public void test2() {
		Bookservlet bookservlet = ioc.getBean(Bookservlet.class);
		bookservlet.doget();
	}

}
