package com.Blog.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.Blog.bean.Tag;
import com.Blog.dao.TagMapper;

@Service
public class TagService implements TagMapper {

	@Autowired
	TagMapper tagMapper;
	
	@Override
	public List<Tag> selectAll() {
		// TODO 自动生成的方法存根
		return tagMapper.selectAll();
	}

	@Override
	public Tag selectById(Long id) {
		// TODO 自动生成的方法存根
		return tagMapper.selectById(id);
	}

	@Override
	public void delete(Long id) {
		// TODO 自动生成的方法存根
		tagMapper.delete(id);
	}

	@Override
	public Tag getTagByName(String name) {
		// TODO 自动生成的方法存根
		return tagMapper.getTagByName(name);
	}

	@Override
	public void addTag(String name) {
		// TODO 自动生成的方法存根
		tagMapper.addTag(name);
	}

	@Override
	public void updateTag(Long id, String name) {
		// TODO 自动生成的方法存根
		tagMapper.updateTag(id, name);
	}

	@Override
	public List<Tag> getBlogTag() {
		// TODO 自动生成的方法存根
		return tagMapper.getBlogTag();
	}

	@Override
	public void insertBlogAndTags(Long tagId, Long blogId) {
		// TODO 自动生成的方法存根
		tagMapper.insertBlogAndTags(tagId, blogId);
	}

	@Override
	public void deleteByBlog(Long blogId) {
		// TODO 自动生成的方法存根
		tagMapper.deleteByBlog(blogId);
	}


}
