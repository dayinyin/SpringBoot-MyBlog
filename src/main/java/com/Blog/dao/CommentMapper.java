package com.Blog.dao;

import java.util.Date;
import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.Blog.bean.Comment;

@Mapper
public interface CommentMapper {

	public List<Comment> selectByBlogId(Long blogId);
	
	public void addComment(@Param("nickname")String nickname,@Param("email")String email
			,@Param("content")String content,@Param("avatar")String avatar,@Param("create_time")Date create_time
			,@Param("blog_id")Long blog_id,@Param("parent_comment_id")Long parent_comment_id,@Param("admincomment")Boolean admincomment);
	
}
