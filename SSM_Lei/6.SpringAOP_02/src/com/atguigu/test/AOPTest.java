package com.atguigu.test;

import static org.junit.Assert.*;

import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.atguigu.impl.MyMathCalculator;
import com.atguigu.inter.Calculator;

public class AOPTest {
	
	ApplicationContext ioc = new ClassPathXmlApplicationContext("applicationContext.xml");

	@Test
	public void test() {
		
		/**
		 * 1 . 从ioc容器中拿到的是目标对象，如果想用类型，一定要用他的接口类型，而不是他本类
		 * 	001 AOP的底层就是动态代理，容器中保存的组件是他的代理对象，
		 */
		MyMathCalculator bean = ioc.getBean(MyMathCalculator.class);
		bean.add(1, 2);
		 
	}

}
