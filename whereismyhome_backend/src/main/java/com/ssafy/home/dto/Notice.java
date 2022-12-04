package com.ssafy.home.dto;

public class Notice {
	
	private int noticeIdx;
	private int userIdx;
	private String title;
	private String content;
	private String registerDate;
	
	public Notice() {}

	public Notice(int noticeIdx, int userIdx, String title, String content, String registerDate) {
		super();
		this.noticeIdx = noticeIdx;
		this.userIdx = userIdx;
		this.title = title;
		this.content = content;
		this.registerDate = registerDate;
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

	@Override
	public String toString() {
		return "NoticeDTO [noticeIdx=" + noticeIdx + ", userIdx=" + userIdx + ", title=" + title + ", content="
				+ content + ", registerDate=" + registerDate + "]";
	}
}
