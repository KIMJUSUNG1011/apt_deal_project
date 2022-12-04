package com.ssafy.home.dto;

import lombok.Data;

@Data
public class User {
	
	private int idx; 
	private String userId; 
	private String pass; 
	private String name; 
	private String phone; 
	private String registerDate; 
	private String withdrawalDate; 
	private String img;
	private FileInfo fileInfo;
	private char type;
}
