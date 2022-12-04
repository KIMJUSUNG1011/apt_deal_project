package com.ssafy.home.model.service;

import java.sql.SQLException;
import java.util.List;

import com.ssafy.home.dto.Notice;
import com.ssafy.home.dto.User_Notice;
import com.ssafy.home.util.PagingVO;

public interface NoticeService {

	// 공지사항 등록
	int add(Notice notice) throws SQLException;

	// 공지사항 수정
	int edit(Notice notice) throws SQLException;

	// 공지사항 삭제
	int delete(int noticeIdx) throws SQLException;
	
	// 공지사항 총 개수
	int countNotice();
	
	// 공지사항 목록 보기
	List<User_Notice> list(PagingVO vo) throws SQLException;

	// 공지사항 상세 보기
	User_Notice view(int noticeIdx) throws SQLException;
}
