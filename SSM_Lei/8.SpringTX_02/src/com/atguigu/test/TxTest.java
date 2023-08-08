package com.atguigu.test;

import static org.junit.Assert.*;

import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.atguigu.service.BookService;

public class TxTest {
	
	ApplicationContext ioc = new ClassPathXmlApplicationContext("application.xml");

	@Test
	public void test() {
		
		BookService bookService = ioc.getBean(BookService.class);
		bookService.checkout("book01", "Tom");
		
	}

}
