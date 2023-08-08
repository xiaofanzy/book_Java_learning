package com.atguigu.Dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

@Component
public class BookDao {
	
	@Autowired
	private JdbcTemplate jdbcTemplate;
	
	public void updateBalance(String username,int balance){
		String sql = "UPDATE# account set balance = balance - ? WHERE username = ?";
		int update = jdbcTemplate.update(sql, balance,username);
		System.out.println("更新成功");
	}
	
	public void updateBook(String bookName){
		String sql = "UPDATE book_stock set stock = stock - 1 WHERE isbn = ?";
		jdbcTemplate.update(sql,bookName);
		System.out.println("更新成功");
	}
	
	public String getIsbn(String username){
		String sql = "select isbn from book where book_name = ?";
		String isbn = jdbcTemplate.queryForObject(sql, String.class,username);
		return isbn;
	}

}
