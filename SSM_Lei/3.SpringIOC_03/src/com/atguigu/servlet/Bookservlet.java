package com.atguigu.servlet;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.atguigu.service.BookService;

@Controller
public class Bookservlet {
	
	@Autowired
	private BookService bookService;
	
	public void doget(){
		
		bookService.save();
	}

}
