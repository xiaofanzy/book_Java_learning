package com.atguigu.service;

import org.springframework.beans.factory.annotation.Autowired;

import com.atguigu.dao.BaseDao;

public class BaseService<T> {
	
	@Autowired
	private BaseDao<T> baseDao;
	
	public void getService(){
		baseDao.save();
	}

}
