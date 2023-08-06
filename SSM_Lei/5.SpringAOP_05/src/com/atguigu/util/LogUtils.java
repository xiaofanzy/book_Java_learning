package com.atguigu.util;

import java.lang.reflect.Method;
import java.util.Arrays;

public class LogUtils {
	
	public static void getMethodBefore(Method method, Object... args){
		System.out.println(method.getName() +" 方法正在使用开始前，参数为" + Arrays.asList(args));
	}
	
	public static void getMethodAfter(Method method, Object... args){
		System.out.println(method.getName() +" 方法正在使用调用结束，参数为" + Arrays.asList(args));
	}
	
	public static void getMethodError(Method method, Exception e){
		System.out.println(method.getName() +" 方法正在使用异常，参数为" + e.getCause());
	}
	
	public static void getMethodFinally(Method method, Object... args){
		System.out.println(method.getName() +" 方法正在使用最终，参数为" + Arrays.asList(args));
	}
	

}
