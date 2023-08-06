package com.atguigu.dao;

import org.springframework.stereotype.Component;

import com.atguigu.bean.User;

@Component
public class UserDao extends BaseDao<User> {

	@Override
	public void save() {
		System.out.println("用户保存成功");
		
	}

}
