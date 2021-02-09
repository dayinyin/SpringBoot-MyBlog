package com.Blog.controller.admin;

import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.Blog.util.MD5Utils;


@Controller
@RequestMapping("/admin")
public class LoginController {
	
	@Autowired
	HttpSession httpSession;
	
	@RequestMapping("/toLogin")
	public String tologin() {
		
		return "admin/login";
	}
	
	
	@RequestMapping("/login")
    public static String login(HttpServletRequest request,String username,String password,RedirectAttributes attributes) {
		
		//获取验证码
        String rightCode = (String) request.getSession().getAttribute("rightCode");
        //前端传入的tryCode参数
        String tryCode = request.getParameter("tryCode");
		
		 Subject subject = SecurityUtils.getSubject();
		 
		 //自己定义得salt和密码混合
		 String mixSource="gjhweqs"+password+"gjfgi";
		 //过一次md5加密
		 MD5Utils md5Utils = new MD5Utils();
		 String mixPassword = md5Utils.MD5_32(mixSource);
		 
         AuthenticationToken token = new UsernamePasswordToken(username, mixPassword);
         
         if (!rightCode.equals(tryCode)) {
    		 attributes.addFlashAttribute("msg", "验证码错误,请再输一次!");
    		 return "redirect:/admin/toLogin";
    	 }else {
    		 try {
                 //对用户进行认证登陆
                 subject.login(token);
                 return "redirect:/admin/toIndex";
             } catch (AuthenticationException e) {
            	 attributes.addFlashAttribute("msg", "用户名或密码错误");
                 return "redirect:/admin/toLogin";
             }
    	 }     
         
	}
	
	@RequestMapping("/toIndex")
    public String toIndex() {
		
		return "admin/index";
	}
	
	//shiro特定得退出步骤
	@RequestMapping("/logout")
	public String logout(){
        //销毁session
    	Subject subject = SecurityUtils.getSubject();
    	subject.logout();
    	httpSession.invalidate();
    	httpSession.removeAttribute("user");
		return "redirect:/admin/toLogin";
	}
	
}
