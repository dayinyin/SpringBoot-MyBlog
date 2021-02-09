package com.Blog.controller;

import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.Blog.bean.Blog;
import com.Blog.service.BlogService;

@Controller
public class ArchiveShowController {

	@Autowired
    private BlogService blogService;

    @RequestMapping("/archives")
    public String archives(Model model) {
    	
    	//查出博客全部年份，因为sql语句不能去重，只能用set去重
    	List<String> selectYear = blogService.selectYear();
    	Set<String> set = new HashSet(selectYear);
    	
    	//通过各个年份select出blog集合
    	 Map<String, List<Blog>> map = new HashMap<String,List<Blog>>();
    	for (String year : set) {
            
			map.put(year, blogService.selectByYear(year));
        }
    	
    	//存放在request域
    	model.addAttribute("archiveMap", map);
    	model.addAttribute("blogCount", blogService.findArchive().size());
        return "archives";
    }
	
    
}
