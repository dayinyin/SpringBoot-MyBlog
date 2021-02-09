package com.Blog.controller.admin;

import java.util.Date;
import java.util.Iterator;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.Blog.bean.Blog;
import com.Blog.bean.Tag;
import com.Blog.bean.User;
import com.Blog.service.BlogService;
import com.Blog.service.TagService;
import com.Blog.service.TypeService;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;

@Controller
@RequestMapping("/admin")
public class BlogController {
	
	@Autowired
	TypeService typeService;
	
	@Autowired
	TagService tagService;
	
	@Autowired
	BlogService blogService;

	public void setTypeAndTag(Model model) {
        model.addAttribute("types", typeService.selectAll());
        model.addAttribute("tags", tagService.selectAll());
    }
	
	@RequestMapping("/blogs")  //后台显示博客列表
    public String blogs(@RequestParam(required = false,defaultValue = "1",value = "pagenum")int pagenum, Model model){
        PageHelper.startPage(pagenum, 10);
        List<Blog> allBlog = blogService.selectAll();
        //得到分页结果对象
        PageInfo pageInfo = new PageInfo(allBlog);
        model.addAttribute("pageInfo", pageInfo);
        //查询类型和标签
        setTypeAndTag(model);  
        return "admin/blogs";
    }
	
	
	@PostMapping("/blogs/search")  //按条件查询博客
    public String searchBlogs(Blog blog, @RequestParam(required = false,defaultValue = "1",value = "pagenum")int pagenum, Model model){
        PageHelper.startPage(pagenum, 10);
        List<Blog> allBlog = blogService.searchBlog(blog.getTypeId(), blog.isRecommend(),blog.getTitle());
        //得到分页结果对象
        PageInfo pageInfo = new PageInfo(allBlog);
        model.addAttribute("pageInfo", pageInfo);
        model.addAttribute("message", "查询成功");
        setTypeAndTag(model);
        return "admin/blogs";
    }
	
    @RequestMapping("/blogs/input") //去新增博客页面
    public String toAddBlog(Model model){
        model.addAttribute("blog", new Blog());  //返回一个blog对象给前端th:object
        setTypeAndTag(model);
        return "admin/blogs-input";
    }
    
    @RequestMapping("/blogs/{id}/input") //去编辑博客页面
    public String toEditBlog(@PathVariable Long id, Model model){
        Blog blog = blogService.selectById(id);
        //遍历tag，变成字符串
        blog.init();   //将tags集合转换为tagIds字符串
        
        model.addAttribute("blog", blog);     //返回一个blog对象给前端th:object
        setTypeAndTag(model);
        return "admin/blogs-input";
    }
    
    @Transactional
    @PostMapping("/blogs") //新增、编辑博客
    public String addBlog(Blog blog, HttpSession session, RedirectAttributes attributes){
        //设置user属性
        blog.setUser((User) session.getAttribute("user"));
        //设置用户id
        blog.setUserId(blog.getUser().getId());
        //设置blog的type
        blog.setType(typeService.getType(blog.getType().getId()));
//        设置blog中typeId属性
        blog.setTypeId(blog.getType().getId());
        
        //blog得内容唯一不重复，重复得内容不能添加
        if(blogService.selectByContent(blog.getContent())!=null) {
        	attributes.addFlashAttribute("msg", "内容重复，不能添加");
            return "redirect:/admin/blogs";
        }
        

        if (blog.getId() == null) {   //id为空，则为新增
        	Date createTime=new Date();
        	blogService.addBlog(blog.getTitle(), blog.getContent(), blog.getFirstPicture(), blog.getFlag(),0
            		, blog.isAppreciation(), 
            		blog.isShareStatement(),blog.isCommentabled(), blog.isPublished(), blog.isRecommend(), createTime, createTime,
            		blog.getTypeId(), blog.getUserId(), blog.getTagIds(), blog.getDescription());
        	
        	//内容不重复，所以通过内容就能定位id（都是唯一不重复）
        	Blog result = blogService.selectByContent(blog.getContent());
        	
        	String tagIds = blog.getTagIds();
        	String[] split = tagIds.split(",");
        	for(int i=0;i<split.length;i++) {
        		//String 类型转Long
        		//添加到blog和tags得关联表进行绑定
        		tagService.insertBlogAndTags(Long.parseLong(split[i]), result.getId());
        	}
        	
            
        } else {
        	//上面为新增，所以这里是修改
        	Date updateTime=new Date();
        	
            blogService.updateBlog(blog.getId(),blog.getTitle(), blog.getContent(), blog.getFirstPicture(), blog.getFlag()
            		, blog.isAppreciation(), 
            		blog.isShareStatement(),blog.isCommentabled(), blog.isPublished(), blog.isRecommend(), updateTime, 
            		blog.getTypeId(), blog.getUserId(), blog.getTagIds(), blog.getDescription());
            
            //先把tag关联表删光，再添加
            tagService.deleteByBlog(blog.getId());
            
            String tagIds = blog.getTagIds();
        	String[] split = tagIds.split(",");
        	for(int i=0;i<split.length;i++) {
        		//String 类型转Long
        		//添加到blog和tags得关联表进行绑定
        		tagService.insertBlogAndTags(Long.parseLong(split[i]), blog.getId());
        	}
            
        }

        attributes.addFlashAttribute("msg", "新增成功");
        return "redirect:/admin/blogs";
    }
    
    @Transactional
    @RequestMapping("/blogs/{id}/delete")
    public String deleteBlogs(@PathVariable Long id, RedirectAttributes attributes){
    	//先把tag关联表删光
    	tagService.deleteByBlog(id);
    	//再删博客
        blogService.deleteBlog(id);
        attributes.addFlashAttribute("msg", "删除成功");
        return "redirect:/admin/blogs";
    }
}
