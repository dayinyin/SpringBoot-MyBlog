package com.Blog.service;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.Blog.bean.Comment;
import com.Blog.dao.CommentMapper;

@Service
public class CommentService implements CommentMapper {

	@Autowired
	CommentMapper commentMapper;

	@Override
	public List<Comment> selectByBlogId(Long blogId) {
		// TODO 自动生成的方法存根
		return commentMapper.selectByBlogId(blogId);
	}

	@Override
	public void addComment(String nickname, String email, String content, String avatar, Date create_time, Long blog_id,
			Long parent_comment_id, Boolean admincomment) {
		// TODO 自动生成的方法存根
		commentMapper.addComment(nickname, email, content, avatar, create_time, blog_id, parent_comment_id, admincomment);
	}
	

}
