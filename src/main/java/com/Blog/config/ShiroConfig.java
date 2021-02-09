package com.Blog.config;

import java.util.HashMap;
import java.util.Map;

import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ShiroConfig {
	
	//Subject,SecurityManager,Realms
	
	//ShiroFilterFactoryBean	创建subject
	@Bean
	public ShiroFilterFactoryBean shiroFilterFactoryBean(@Qualifier("defaultWebSecurityManager")DefaultWebSecurityManager defaultWebSecurityManager) {
		ShiroFilterFactoryBean bean = new ShiroFilterFactoryBean();
		//设置管理器
		bean.setSecurityManager(defaultWebSecurityManager);
		
		Map<String, String> filterChainDefinitionMap = new HashMap<String, String>();
		/*
		 * 进入页面要通关认证
		 * */
		filterChainDefinitionMap.put("/admin/toIndex", "authc");
		filterChainDefinitionMap.put("/admin/blogs", "authc");
		filterChainDefinitionMap.put("/admin/blogs/**", "authc");
		filterChainDefinitionMap.put("/admin/tags", "authc");
		filterChainDefinitionMap.put("/admin/tags/**", "authc");
		filterChainDefinitionMap.put("/admin/types", "authc");
		filterChainDefinitionMap.put("/admin/types/**", "authc");
		
		//拦截器
		bean.setFilterChainDefinitionMap(filterChainDefinitionMap);
		
		//设置登录请求
		bean.setLoginUrl("/admin/toLogin");
				
		//设置未授权
//		bean.setUnauthorizedUrl("");
		
		return bean;
	}
	
	
	//DafaultwebsecurityManager   管理器——subject
	@Bean
	public DefaultWebSecurityManager defaultWebSecurityManager(@Qualifier("userRealm")UserRealm userRealm) {
		DefaultWebSecurityManager defaultWebSecurityManager = new DefaultWebSecurityManager();
		defaultWebSecurityManager.setRealm(userRealm);
		return defaultWebSecurityManager;
	}
	
	//创建realm对象，自定义
	@Bean
	public UserRealm userRealm() {
		
		return new UserRealm();
	} 
}
