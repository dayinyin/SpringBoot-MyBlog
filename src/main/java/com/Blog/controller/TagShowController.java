package com.Blog.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.Blog.bean.Blog;
import com.Blog.bean.Tag;
import com.Blog.service.BlogService;
import com.Blog.service.TagService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;

@Controller
public class TagShowController {
	
	@Autowired
	TagService tagService;
	
	@Autowired
	BlogService blogService;

	@RequestMapping("/tags/{id}")
    public String types(@PathVariable Long id, @RequestParam(required = false,defaultValue = "1",value = "pageNum")int pagenum,
                        Model model){
        PageHelper.startPage(pagenum, 999);  //开启分页
        
        //获取全部blog关联的tag，因为一个blog可以关联多个tag，一对多的关系，tags这个集合都是都有blog对应的tag，列出全部tag
        List<Tag> tags = tagService.getBlogTag();
        
        System.out.println(tags.size());
        //-1从导航点过来的
        if (id == -1){
        	//获取集合第一个元素
        	//确保肯定有一个tag得id
            id = tags.get(0).getId();
        }
        //通过tag的id获取对应的blog，一个博客标签对应多个文章，所以获取blog集合
        List<Blog> blogs = blogService.getByTagId(id);
        
        System.out.println(id+"======"+blogs.size());
        PageInfo<Blog> pageInfo = new PageInfo<>(blogs);
        
        model.addAttribute("tags", tags);
        model.addAttribute("pageInfo", pageInfo);
        model.addAttribute("activeTagId", id);

        return "tags";
    }
}
