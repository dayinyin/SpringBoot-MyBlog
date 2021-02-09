package com.Blog.service;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.Blog.bean.Type;
import com.Blog.dao.TypeMapper;

@Service
public class TypeService implements TypeMapper {
	
	@Autowired
	TypeMapper typeMapper;

	@Override
	public List<Type> selectAll() {
		// TODO 自动生成的方法存根
		List<Type> selectAll = typeMapper.selectAll();
		return selectAll;
	}

	@Override
	public Type getType(Long id) {
		// TODO 自动生成的方法存根
		return typeMapper.getType(id);
	}

	@Override
	public Type getTypeByName(String name) {
		// TODO 自动生成的方法存根
		return typeMapper.getTypeByName(name);
	}

	@Override
	public void addType(String name) {
		// TODO 自动生成的方法存根
		typeMapper.addType(name);
	}

	@Override
	public void updateType(Integer id, String name) {
		// TODO 自动生成的方法存根
		typeMapper.updateType(id, name);
	}

	@Override
	public void deleteType(Integer id) {
		// TODO 自动生成的方法存根
		typeMapper.deleteType(id);
	}

	@Override
	public List<Type> getBlogType() {
		// TODO 自动生成的方法存根
		return typeMapper.getBlogType();
	}


}
