package com.ssafy.home.model.mapper;

import java.sql.SQLException;

import org.apache.ibatis.annotations.Mapper;

import com.ssafy.home.dto.User;

@Mapper
public interface UserMapper {

	// 회원 가입
	void add(User user) throws SQLException;

	// 로그인
	User login(String userId, String pass) throws SQLException;
	
	//비밀 번호 찾기
	User findPass(String userId,String name) throws SQLException;

	// 회원 정보 조회
	User view(int id) throws SQLException;
	
	// 회원 정보 수정
	int edit(User user) throws SQLException;

	// 회원 탈퇴
	int delete(String userId, String pass) throws SQLException;
	
	// 이미지 삽입
	void insertImg(User user) throws SQLException;

	void setUserImg(User user) throws SQLException;

	int idCheck(User user) throws SQLException;
}
