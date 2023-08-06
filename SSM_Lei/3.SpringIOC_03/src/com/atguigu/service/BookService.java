package com.atguigu.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.atguigu.dao.BookDao;

@Service
public class BookService {
	
	/**
	 * autowired 原理： 
	 * 1. 先按照类型去容器中找到对应的组件；
	 * 	1. 如果找到一个，就赋值；
	 * 	2. 如果找到多个， 就按照变量名作为id继续匹配， 就是对象名 类似于BookDao bookDao 里面的 bookDao
	 * 	3. 如果没有找到就报错；
	 * 
	 * @Qualifier("bookDao") 指定通过@Qualifier 内部的变量作为变量名去匹配
	 * 
	 * required=false 如果autowired 找到，就装配，找不到就装配null
	 * 
	 * 
	 * @Authowired 和 @Resource  相比， @Authowired 是spring提供的，性能更强大，@Resource是java提供的，兼容性更好；
	 */
	@Qualifier("bookDao")
	@Autowired(required=false)
	private BookDao bookDao;

	public void save() {
		System.out.println("BookService正造保存");
		bookDao.saveBook();
	}
	
	
	/**
	 * 方法上也可以作用到autowried注解
	 * @param bookService
	 * @param bookDao
	 */
	/*@Autowired
	public void haha(BookService bookService,BookDao bookDao){
		System.out.println("111");
	}*/

}
