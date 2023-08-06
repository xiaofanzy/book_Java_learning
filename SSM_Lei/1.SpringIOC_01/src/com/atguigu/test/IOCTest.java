package com.atguigu.test;

import static org.junit.Assert.*;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;
import java.util.Properties;

import javax.sql.DataSource;

import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.util.SocketUtils;

import com.atguigu.bean.Book;
import com.atguigu.bean.Car;
import com.atguigu.bean.Person;

public class IOCTest {
	
	private ApplicationContext ioc = new ClassPathXmlApplicationContext("ioc.xml");
	private ApplicationContext ioc2 = new ClassPathXmlApplicationContext("ioc2.xml");
	private ConfigurableApplicationContext ioc3 = new ClassPathXmlApplicationContext("ioc3.xml");
	private ApplicationContext ioc4 = new ClassPathXmlApplicationContext("applicationContext.xml");
	private ApplicationContext ioc5 = new ClassPathXmlApplicationContext("applicationContext2.xml");

	/**
	 * 从容其中拿到这个组件
	 * 
	 * 存在问题： 1.所有源码包里面的东西都会合并放到类路径下；
	 * 		  2. 如果没有创建bean的时候，会报这个错： org.springframework.beans.factory.NoSuchBeanDefinitionException: No bean named 'person02' is defined


	 */

	@Test
	public void test() {
		Person person = (Person) ioc.getBean("person01");
		Person person1 = (Person) ioc.getBean("person01");
		System.out.println(person == person1);
		
		//Person person2 = (Person) ioc.getBean("person02");

	}
	
	
	/**
	 * 根据bean类型从ioc容器中获取
	 * 当根据bean类型获取组件的时候，如果存在两个相同类型的组件，就会报错；
	 * org.springframework.beans.factory.NoUniqueBeanDefinitionException: No qualifying bean of type [com.atguigu.bean.Person] is defined: expected single matching bean but found 2: person01,person02
	
	 */
	@Test
	public void test02() {
		Person person = ioc.getBean("person02",Person.class);
		System.out.println(person);
		// 当存在两个的时候；报错；
		
	}
	
	@Test
	public void test03(){
		Person person = (Person)ioc.getBean("person03");
		System.out.println(person);
		
		Person person1 = (Person)ioc.getBean("person05");
		System.out.println(person1);
		
		Person person2 = (Person)ioc.getBean("person06");
		System.out.println(person2);
	}
	
	/**
	 * 测试使用null值，默认引用类型就是null 基本类型是默认值
	 */
	@Test
	public void test04(){
		Person person01 = (Person)ioc2.getBean("person01");
		System.out.println(person01);
		List<Book> books = person01.getBook();
		System.out.println(books);
		
		Map maps = person01.getMaps();
		System.out.println(maps);
		
		
		Properties properties = person01.getProperties();
		System.out.println(properties.get("username"));
		
		Map<String,Object> bean = (Map<String, Object>) ioc2.getBean("utilmap");
		System.out.println(bean.getClass());
	}
	
	@Test
	public void test05(){
		Person person = (Person)ioc2.getBean("person02");
		System.out.println(person.getCar().getPrice());
		Car car01 = (Car)ioc2.getBean("car01");
		System.out.println(car01.getPrice());
		
		Book bean = (Book)ioc2.getBean("book02");
		System.out.println(bean.getBookName());
		
	}
	
	/**
	 * 单实例： 
	 * 		2. 容器启动 构造器 -》 初始化方法 -> 容器关闭 销毁方法
	 * 多实例：
	 * 		获取bean(构造器 -》 初始化方法 -》 容器关闭不会调用bean的销毁方法)
	 * 
	 * 后置处理器：
	 *    容器启动 构造器 -》 postProcessBeforeInitialization -》 初始化方法  -》 postProcessAfterInitialization-> 容器关闭 销毁方法
	 *    
	 *    无论bena是否有初始化方法，后置处理器都会默认其有，还会继续工作
	 */
	@Test
	public void test06(){
		/*System.out.println("=======");
		Object bean = ioc3.getBean("myFactory");
		System.out.println(bean);*/
		
		Object bean2 = ioc3.getBean("book");
		System.out.println(bean2);
		ioc3.close();
	}
	
	@Test
	public void test07() throws SQLException{
		DataSource bean = (DataSource)ioc4.getBean("dataSource");
		System.out.println(bean.getConnection());
	}
	
	@Test
	public void test08(){
		/*Person bean = ioc5.getBean(Person.class);
		System.out.println(bean);*/
		
		Person bean2 = (Person) ioc5.getBean("person1");
		System.out.println(bean2);
	}
	

}
