package com.Blog.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.Blog.bean.User;

@Mapper
public interface UserMapper {

	public List<User> selectAll();
	
	public User login(String username);
}
