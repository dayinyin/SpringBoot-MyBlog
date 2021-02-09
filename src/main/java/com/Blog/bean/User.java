package com.Blog.bean;

import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class User {

	private Long id;
	private String nickname;
	private String username;
	private String password;
	private String email;
	private String avatar;
	private Integer type;
	private Date create_time;
	private Date update_time;
	
	
	
	
}
