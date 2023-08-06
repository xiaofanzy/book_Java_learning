package com.atguigu.dao;

import org.springframework.stereotype.Repository;

/**
 * 给类加注解标签 ("bookdao")
 * @author 凡是六一
 *
 */
@Repository
public class BookDao {

	public void saveBook() {
		System.out.println("图书已保存");
		
	}

}
