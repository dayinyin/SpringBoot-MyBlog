package com.Blog.bean;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Type {

	private Long id;
	private String name;
	
	private List<Blog> blogs = new ArrayList<>();
}
