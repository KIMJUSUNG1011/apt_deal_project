package com.ssafy.home.model.service;

import java.io.UnsupportedEncodingException;
import java.sql.SQLException;

import javax.mail.MessagingException;

import com.ssafy.home.dto.EmailData;
import com.ssafy.home.dto.User;

public interface UserService {

	// 회원 가입
	void add(User user) throws SQLException;

	// 로그인
	User login(String userId, String pass) throws SQLException;
	
	// 비밀 번호 찾기
	User findPass(String userId,String name) throws SQLException;

	// 새로운 비밀번호 생성
	String makeRandomPass(int len);
	
	// 회원 정보 조회
	User view(int id) throws SQLException;
	
	// 회원 정보 수정
	int edit(User user) throws SQLException;

	// 회원 탈퇴
	int delete(String userId, String pass) throws SQLException;
	
	// 이미지 삽입
	void insertImg(User user) throws SQLException;
	void setUserImg(User user)throws SQLException;
	
	// 이메일 보내기
	void sendEmail(EmailData emailData) throws UnsupportedEncodingException, MessagingException;

	void sendPassEmail(EmailData emailData)throws UnsupportedEncodingException, MessagingException;

	// 아이디 중복체크
	int idCheck(User user) throws SQLException;
}
