package com.Blog.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.Blog.bean.Type;

@Mapper
public interface TypeMapper {

	public List<Type> selectAll();
	
	public Type getType(Long id);
	
	public Type getTypeByName(String name);
	
	public void addType(String name);
	
	public void updateType(@Param("id")Integer id,@Param("name")String name);
	
	public void deleteType(Integer id);
	
	public List<Type> getBlogType();
	
}
