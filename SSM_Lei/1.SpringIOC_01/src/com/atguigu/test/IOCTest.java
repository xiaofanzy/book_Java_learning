package com.atguigu.test;

import static org.junit.Assert.*;

import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.atguigu.bean.Person;

public class IOCTest {
	
	/**
	 * 从容其中拿到这个组件
	 */

	@Test
	public void test(){
		ApplicationContext ioc = new ClassPathXmlApplicationContext("ioc.xml");
		Person person = (Person)ioc.getBean("person01");
		System.out.println(person.getAge());
	
	
	}

}