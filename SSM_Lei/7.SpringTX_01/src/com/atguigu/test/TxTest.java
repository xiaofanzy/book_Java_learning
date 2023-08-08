package com.atguigu.test;

import static org.junit.Assert.*;

import java.sql.Connection;
import java.sql.SQLException;

import javax.sql.DataSource;

import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;

import com.mysql.cj.jdbc.JdbcConnection;

public class TxTest {
	
	ApplicationContext ioc = new ClassPathXmlApplicationContext("applicationContext.xml");

	private JdbcTemplate bean = ioc.getBean(JdbcTemplate.class);
	
	private final String updateSql = "update employee e set e.salary=200 where e.emp_name=?";
	
	@Test
	public void test() throws SQLException {
		DataSource bean = ioc.getBean(DataSource.class);
		Connection connection = bean.getConnection();
		System.out.println(connection);
		
	}
	
	@Test
	public void test1(){
	
		int update = bean.update(updateSql, new Object[]{"Susan"});
		System.out.println(update);
		
	}
	
	@Test
	public void test2(){
		// 查询并将结果封装成对象
		Object queryForObject = bean.queryForObject(updateSql, new BeanPropertyRowMapper(Integer.class),new Object[]{"Susan"});
		
	}

}
