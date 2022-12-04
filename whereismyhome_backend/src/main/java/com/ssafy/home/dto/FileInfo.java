package com.ssafy.home.dto;

import lombok.Data;

@Data
public class FileInfo {
	private String userId;
	private String originFile;
	private String saveFile;
	private String saveFolder;
}
