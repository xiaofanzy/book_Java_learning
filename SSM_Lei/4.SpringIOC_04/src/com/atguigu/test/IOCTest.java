package com.atguigu.test;

import static org.junit.Assert.*;

import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.atguigu.service.BookService;
import com.atguigu.service.UserService;

public class IOCTest {
	
	ApplicationContext ioc = new ClassPathXmlApplicationContext("applicationContext.xml");
	

	@Test
	public void test() {
		BookService bookService = ioc.getBean(BookService.class);
		UserService userService = ioc.getBean(UserService.class);
		bookService.getService();
		userService.getService();
	}

}
