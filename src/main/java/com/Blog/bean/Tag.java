package com.Blog.bean;

import java.util.ArrayList;
import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Tag {
	
	private Long id;
	private String name;

	private List<Blog> blogs = new ArrayList<>();
}
