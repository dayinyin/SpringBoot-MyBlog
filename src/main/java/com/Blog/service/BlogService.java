package com.Blog.service;

import java.util.Date;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.Blog.bean.Blog;
import com.Blog.dao.BlogMapper;

@Service
public class BlogService implements BlogMapper{

	@Autowired
	BlogMapper blogMapper;
	
	@Override
	public List<Blog> selectAll() {
		// TODO 自动生成的方法存根
		return blogMapper.selectAll();
	}

	@Override
	public List<Blog> searchBlog(Long typeId, Boolean recommend, String title) {
		// TODO 自动生成的方法存根
		return blogMapper.searchBlog(typeId, recommend, title);
	}

	@Override
	public void addBlog(String title, String content, String firstPicture, String flag, Integer views,
			Boolean appreciation, Boolean shareStatement, Boolean commentabled, Boolean published, Boolean recommend,
			Date createTime,Date updateTime, Long typeId, Long userId, String tagIds, String description) {
		// TODO 自动生成的方法存根
		blogMapper.addBlog(title, content, firstPicture, flag, views, appreciation, shareStatement, commentabled, published, recommend, createTime, updateTime,typeId, userId, tagIds, description);
	}

	@Override
	public void updateBlog(Long id, String title, String content, String firstPicture, String flag,
			Boolean appreciation, Boolean shareStatement, Boolean commentabled, Boolean published, Boolean recommend,
			Date updateTime, Long typeId, Long userId, String tagIds, String description) {
		// TODO 自动生成的方法存根
		blogMapper.updateBlog(id, title, content, firstPicture, flag, appreciation, shareStatement, commentabled, published, recommend, updateTime, typeId, userId, tagIds, description);
	}

	@Override
	public Blog selectById(Long id) {
		// TODO 自动生成的方法存根
		return blogMapper.selectById(id);
	}

	@Override
	public void deleteBlog(Long id) {
		// TODO 自动生成的方法存根
		blogMapper.deleteBlog(id);
	}

	@Override
	public List<Blog> getAllRecommendBlog() {
		// TODO 自动生成的方法存根
		return blogMapper.getAllRecommendBlog();
	}

	@Override
	public List<Blog> showBlog() {
		// TODO 自动生成的方法存根
		return blogMapper.showBlog();
	}

	@Override
	public Blog selectByContent(String content) {
		// TODO 自动生成的方法存根
		return blogMapper.selectByContent(content);
	}

	@Override
	public List<Blog> getSearchBlog(String title) {
		// TODO 自动生成的方法存根
		return blogMapper.getSearchBlog(title);
	}

	@Override
	public List<Blog> getByTagId(Long tagId) {
		// TODO 自动生成的方法存根
		return blogMapper.getByTagId(tagId);
	}

	@Override
	public List<Blog> getByTypeId(Long typeId) {
		// TODO 自动生成的方法存根
		return blogMapper.getByTypeId(typeId);
	}

	@Override
	public Blog getDetailedBlog(Long id) {
		// TODO 自动生成的方法存根
		return blogMapper.getDetailedBlog(id);
	}

	@Override
	public Blog getCommentByBlogId(Long id) {
		// TODO 自动生成的方法存根
		return blogMapper.getCommentByBlogId(id);
	}

	@Override
	public List<String> selectYear() {
		// TODO 自动生成的方法存根
		return blogMapper.selectYear();
	}

	@Override
	public List<Blog> selectByYear(String year) {
		// TODO 自动生成的方法存根
		return blogMapper.selectByYear(year);
	}

	@Override
	public List<Blog> findArchive() {
		// TODO 自动生成的方法存根
		return blogMapper.findArchive();
	}

	@Override
	public void updateViews(Long id, Integer views) {
		// TODO 自动生成的方法存根
		blogMapper.updateViews(id, views);
	}


}
