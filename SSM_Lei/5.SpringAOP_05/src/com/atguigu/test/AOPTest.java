package com.atguigu.test;

import static org.junit.Assert.*;

import org.junit.Test;

import com.atguigu.impl.MyMathCalculator;
import com.atguigu.inter.Calculator;
import com.atguigu.proxy.MyCalcalProxy;

public class AOPTest {

	@Test
	public void test() {
		Calculator calculator = new MyMathCalculator();
		int result = calculator.add(1, 2);
		System.out.println(result);
		
		
		// 使用动态代理
		Calculator proxy = MyCalcalProxy.getProxy(calculator);
		proxy.add(1,2);
		
	}

}
