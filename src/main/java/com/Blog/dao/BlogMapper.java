package com.Blog.dao;

import java.util.Date;
import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.Blog.bean.Blog;

@Mapper
public interface BlogMapper {

	public List<Blog> selectAll();
	
	public List<Blog> searchBlog(@Param("typeId")Long typeId,@Param("recommend")Boolean recommend,@Param("title")String title);
	
	public Blog selectById(Long id);
	
	public void addBlog(@Param("title")String title,@Param("content")String content,@Param("first_picture")String firstPicture,
			@Param("flag")String flag,@Param("views")Integer views,@Param("appreciation")Boolean appreciation,
			@Param("share_statement")Boolean shareStatement,@Param("commentabled")Boolean commentabled,@Param("published")Boolean published,
			@Param("recommend")Boolean recommend,@Param("create_time")Date createTime,@Param("update_time")Date updateTime,
			@Param("type_id")Long typeId,@Param("user_id")Long userId,
			@Param("tag_ids")String tagIds,@Param("description")String description);
	
	public void updateBlog(@Param("id")Long id,@Param("title")String title,@Param("content")String content,@Param("first_picture")String firstPicture,
			@Param("flag")String flag,@Param("appreciation")Boolean appreciation,
			@Param("share_statement")Boolean shareStatement,@Param("commentabled")Boolean commentabled,@Param("published")Boolean published,
			@Param("recommend")Boolean recommend,
			@Param("update_time")Date updateTime,@Param("type_id")Long typeId,@Param("user_id")Long userId,
			@Param("tag_ids")String tagIds,@Param("description")String description);
	
	
	public void deleteBlog(Long id);
	
	public List<Blog> getAllRecommendBlog();
	
	public List<Blog> showBlog();
	
	public Blog selectByContent(String content);
	
	public List<Blog> getSearchBlog(String title);
	
	public List<Blog> getByTagId(Long tagId);
	
	public List<Blog> getByTypeId(Long typeId);
	
	public Blog getDetailedBlog(Long id);
	
	public Blog getCommentByBlogId(Long id);
	
	public List<String> selectYear();
	
	public List<Blog> selectByYear(String year);
	
	public List<Blog> findArchive();
	
	public void updateViews(@Param("id")Long id ,@Param("views") Integer views );
}
