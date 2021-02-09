package com.Blog.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.Blog.bean.User;
import com.Blog.dao.UserMapper;

@Service
public class UserService implements UserMapper{

	@Autowired
	UserMapper userMapper;

	@Override
	public List<User> selectAll() {
		List<User> selectAll = userMapper.selectAll();
		return selectAll;
	}

	@Override
	public User login(String username) {
		// TODO 自动生成的方法存根
		User login = userMapper.login(username);
		return login;
	}
	
	
	
}
