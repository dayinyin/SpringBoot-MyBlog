package com.Blog.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.Blog.bean.Tag;

@Mapper
public interface TagMapper {

	public List<Tag> selectAll();
	
	public Tag selectById(Long id);
	
	public void delete(Long id);
	
	public Tag getTagByName(String name);
	
	public void addTag(String name);
	
	public void updateTag(@Param("id")Long id,@Param("name")String name);
	
	public List<Tag> getBlogTag();
	
	public void insertBlogAndTags(@Param("tag_id")Long tagId,@Param("blog_id")Long blogId);
	
	public void deleteByBlog(Long blogId);
}
