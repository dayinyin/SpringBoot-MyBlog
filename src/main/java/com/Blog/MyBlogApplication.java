package com.Blog;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;

import com.github.pagehelper.autoconfigure.PageHelperAutoConfiguration;

@SpringBootApplication(exclude = PageHelperAutoConfiguration.class)
public class MyBlogApplication extends SpringBootServletInitializer{

	public static void main(String[] args) {
		SpringApplication.run(MyBlogApplication.class, args);
	}


	protected SpringApplicationBuilder configure(SpringApplicationBuilder builder) {
	return builder.sources(MyBlogApplication.class);

	}
}
