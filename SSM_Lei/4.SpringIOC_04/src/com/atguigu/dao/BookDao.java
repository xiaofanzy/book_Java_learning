package com.atguigu.dao;

import org.springframework.stereotype.Component;

import com.atguigu.bean.Book;

@Component
public  class BookDao  extends BaseDao<Book>{

	@Override
	public void save() {
		System.out.println("BookDao 保存");
		
	}

}
