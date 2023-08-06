package com.atguigu.test;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.atguigu.service.BookService;
import com.atguigu.servlet.Bookservlet;

@ContextConfiguration(locations="classpath:applicationContext.xml")
@RunWith(SpringJUnit4ClassRunner.class)
public class IOCTest2 {
	
	@Autowired
	private Bookservlet bookservlet;
	
	@Test
	public void test01(){
		bookservlet.doget();
	}

}
