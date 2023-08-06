package com.atguigu.factory;

import org.springframework.beans.factory.FactoryBean;

import com.atguigu.bean.Book;

public class MyFactoryBeanImle implements FactoryBean<Book> {

	public Book getObject() throws Exception {
		Book book = new Book();
		book.setAuthor("zhangyu");
		book.setBookName("love");
		return book;
	}

	public Class<?> getObjectType() {
		
		return Book.class;
	}

	public boolean isSingleton() {
		return true;
	}

}
