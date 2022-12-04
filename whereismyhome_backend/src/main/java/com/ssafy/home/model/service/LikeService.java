package com.ssafy.home.model.service;

import java.sql.SQLException;
import java.util.List;

import com.ssafy.home.dto.Like;

public interface LikeService {

	// 관심 지역 등록
	int registLikeApt(int user_idx, String sidoName, String gugunName, String dongName) throws SQLException;

	// 관심 지역 조회
	List<Like> getLikeRegion(int user_idx) throws SQLException;

	// 관심 지역 삭제
	int deleteLikeRegion(int like_idx) throws SQLException;
}
