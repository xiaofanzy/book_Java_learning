package com.atguigu.proxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

import com.atguigu.inter.Calculator;
import com.atguigu.util.LogUtils;

public class MyCalcalProxy {

	public static Calculator getProxy(final Calculator calculator) {
		
		
		ClassLoader loader = calculator.getClass().getClassLoader();
		Class<?>[] interfaces = calculator.getClass().getInterfaces();
		InvocationHandler h = new InvocationHandler() {
			
			public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
				
				Object invoke = null;
				try {
					LogUtils.getMethodBefore(method, args);
					invoke = method.invoke(calculator, args);
					LogUtils.getMethodAfter(method, args);
				} catch (Exception e) {
					LogUtils.getMethodError(method, e);
				}finally{
					LogUtils.getMethodFinally(method, args);
				}
				return invoke;
			}
		};
		Calculator calculators = (Calculator)Proxy.newProxyInstance(loader, interfaces, h);
		
		return calculators;
	}

}
