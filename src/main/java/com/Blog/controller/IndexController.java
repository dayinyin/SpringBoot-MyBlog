package com.Blog.controller;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.Blog.bean.Blog;
import com.Blog.bean.Tag;
import com.Blog.bean.Type;
import com.Blog.handler.NotFoundException;
import com.Blog.service.BlogService;
import com.Blog.service.TagService;
import com.Blog.service.TypeService;
import com.Blog.util.MarkdownUtils;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;

@Controller
public class IndexController {
	
	@Autowired
	HttpSession httpSession;
	
	@Autowired
	BlogService blogService;
	
	@Autowired
	TypeService typeService;
	
	@Autowired
	TagService tagService;

	@RequestMapping(value = "toIndex")
	public String toIndex(@RequestParam(required = false,defaultValue = "1",value = "pageNum")int pagenum, Model model) {
		 	PageHelper.startPage(pagenum, 10);
		 	//获取全部得博客集合
	        List<Blog> allBlog = blogService.showBlog();
	        List<Type> allType = typeService.getBlogType();  //获取博客的类型(联表查询)
	        List<Tag> allTag = tagService.getBlogTag();  //获取博客的标签(联表查询)
	        List<Blog> recommendBlog =blogService.getAllRecommendBlog();  //获取推荐博客

	        //得到分页结果对象
	        PageInfo pageInfo = new PageInfo(allBlog);
	        model.addAttribute("pageInfo", pageInfo);
	        model.addAttribute("tags", allTag);
	        model.addAttribute("types", allType);
	        model.addAttribute("recommendBlogs", recommendBlog);
	        return "index";
	}
	
	//搜索框根据title来模糊查询
	@PostMapping("/search")
    public String search(@RequestParam(required = false,defaultValue = "1",value = "pageNum")int pagenum,
                         @RequestParam(value = "query")String query, Model model){

        PageHelper.startPage(pagenum, 10);
        List<Blog> searchBlog = blogService.getSearchBlog(query);
        PageInfo pageInfo = new PageInfo(searchBlog);
        model.addAttribute("pageInfo", pageInfo);
        model.addAttribute("query", query);
        return "search";
    }

	
	//进入指定得博客页面
    @RequestMapping("/blog/{id}")
    public String toLogin(@PathVariable Long id, Model model){
    	
    	//根据id获取博客全部信息
        Blog blog = blogService.getDetailedBlog(id);
        
        //浏览数量+1
        if(httpSession.getAttribute(id.toString())==null) {
        	blogService.updateViews(blog.getId(), blog.getViews()+1);
        	 httpSession.setAttribute(id.toString(), 1);
        }
        
        
        //博客不存在抛异常
        if (blog == null) {
            throw new NotFoundException("该博客不存在");
        }
        
        //把markdown格式转换成html渲染
        String content = blog.getContent();
        blog.setContent(MarkdownUtils.markdownToHtmlExtensions(content));
        
        model.addAttribute("blog", blog);
        return "blog";
    }
}
