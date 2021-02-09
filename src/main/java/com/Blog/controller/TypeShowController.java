package com.Blog.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.Blog.bean.Blog;
import com.Blog.bean.Type;
import com.Blog.service.BlogService;
import com.Blog.service.TypeService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;

@Controller
public class TypeShowController {
	
	@Autowired
	TypeService typeService;
	
	@Autowired
	BlogService blogService;
	
	 @RequestMapping("/types/{id}")
	    public String types(@PathVariable Long id, @RequestParam(required = false,defaultValue = "1",value = "pageNum")int pagenum,
	                        Model model){
	        PageHelper.startPage(pagenum, 999);  //开启分页
	        
	        //一个type对应多个blog，通过多表联合查出全部的type，（type都是有对应的）
	        List<Type> types = typeService.getBlogType();
	        System.out.println(types.size());
	        //-1从导航点过来的
	        if (id == -1){
	        	//确保有一个type得id
	            id = types.get(0).getId();
	        }
	        
	        //通过一个type的id查出多个Blog，集合Blogs
	        List<Blog> blogs = blogService.getByTypeId(id);
	        System.out.println(blogs.size());
	        PageInfo<Blog> pageInfo = new PageInfo<>(blogs);
	        model.addAttribute("types", types);
	        model.addAttribute("pageInfo", pageInfo);
	        model.addAttribute("activeTypeId", id);

	        return "types";
	    }
}
