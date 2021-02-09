package com.Blog.controller;

import java.util.Date;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import com.Blog.bean.Comment;
import com.Blog.bean.User;
import com.Blog.service.BlogService;
import com.Blog.service.CommentService;

@Controller
public class CommentController {
	
	@Autowired
	HttpSession httpSession;
	
	@Autowired
	CommentService commentService;
	
	@Autowired
	BlogService blogService;
	
	String avatar="/images/avatar.jpg";
	
	@GetMapping("/comments/{blogId}")  //展示留言
    public String comments(@PathVariable Long blogId, Model model){
		
		//通过博客id找出对应得评论集合
        model.addAttribute("comments", commentService.selectByBlogId(blogId));
        //根据blogid，找blog信息
        model.addAttribute("blog", blogService.getDetailedBlog(blogId));
        return "blog :: commentList";
    }

    @PostMapping("/comments")   //提交留言
    public String post(Comment comment){
    	
    	//通过评论获取blog得id
        Long blogId = comment.getBlog().getId();
        //根据blog 得id获取blog（多个表联合查询）
        comment.setBlog(blogService.getDetailedBlog(blogId));  //绑定博客与评论
        comment.setBlogId(blogId);
        
      //获得父id
        Long parentCommentId = comment.getParentComment().getId();
        //没有父级评论默认是-1
        if (parentCommentId != -1) {
            //有父级评论
        	comment.setParentCommentId(parentCommentId);
        } else {
            //没有父级评论
            comment.setParentCommentId((long) -1);
            comment.setParentComment(null);
        }
        
        //知道你是否为管理员，得提前登录一次
        User user = (User) httpSession.getAttribute("user");
        if (user != null){   //用户为管理员
        	//设置评论头像为管理员头像
            comment.setAvatar(user.getAvatar());
            //是否为管理员
            comment.setAdminComment(true);
            
            
            avatar=user.getAvatar();
            Date create_time = new Date();
    		commentService.addComment(user.getNickname(), user.getEmail(), comment.getContent(), avatar, create_time , blogId, comment.getParentCommentId(), comment.isAdminComment());
    		
    		
        }else {
        	
        	//默认得游客评论头像
            comment.setAvatar(avatar);
            Date create_time = new Date();
    		commentService.addComment(comment.getNickname(), comment.getEmail(), comment.getContent(), comment.getAvatar(), create_time , blogId, comment.getParentCommentId(), comment.isAdminComment());
    		
    		
        }

        return "redirect:/comments/" + blogId;
    }
	

}
