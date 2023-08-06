package com.atguigu.inter;

/**
 * 接口一半不加载在容器中，实际上可以加，加了只是告诉spring ioc容器中可能有这种类型的组件而已
 * @author 凡是六一
 *
 */
public interface Calculator {
	
	int add(int i,int j);
	int div(int i,int j);
	int mul(int i,int j);
	int sub(int i,int j);
}
