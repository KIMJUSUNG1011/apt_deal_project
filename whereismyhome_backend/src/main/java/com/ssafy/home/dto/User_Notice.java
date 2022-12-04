package com.ssafy.home.dto;

public class User_Notice {
	private int noticeIdx;
	private int userIdx;
	private String title;
	private String content;
	private String registerDate;
	private String name;
	private String userId;
	
	public User_Notice() {}

	public User_Notice(int noticeIdx, int userIdx, String title, String content, String registerDate, String name, String userId) {
		super();
		this.noticeIdx = noticeIdx;
		this.userIdx = userIdx;
		this.title = title;
		this.content = content;
		this.registerDate = registerDate;
		this.name = name;
		this.userId = userId;
	}

	public int getNoticeIdx() {
		return noticeIdx;
	}

	public void setNoticeIdx(int noticeIdx) {
		this.noticeIdx = noticeIdx;
	}

	public int getUserIdx() {
		return userIdx;
	}

	public void setUserIdx(int userIdx) {
		this.userIdx = userIdx;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public String getRegisterDate() {
		return registerDate;
	}

	public void setRegisterDate(String registerDate) {
		this.registerDate = registerDate;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}
	
	@Override
	public String toString() {
		return "User_Notice [noticeIdx=" + noticeIdx + ", userIdx=" + userIdx + ", title=" + title + ", content="
				+ content + ", registerDate=" + registerDate + ", name=" + name + ", userId=" + userId + "]";
	}
	
}
