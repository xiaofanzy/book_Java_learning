package com.atguigu.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.atguigu.Dao.BookDao;

@Service
public class BookService {
	
	
	@Autowired
	private BookDao bookDao;
	
	@Transactional
	public void checkout(String bookName,String userName){
		String isbn = bookDao.getIsbn(bookName);
		bookDao.updateBook(isbn);
		bookDao.updateBalance(userName, 1);
	}

}
