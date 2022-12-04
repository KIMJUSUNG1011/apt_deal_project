package com.ssafy.home.model.mapper;

import java.sql.SQLException;
import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.ssafy.home.dto.Like;

@Mapper
public interface LikeMapper {

	// 관심 지역 등록
	int registLikeApt(int user_idx, String sidoName, String gugunName, String dongName) throws SQLException;

	// 관심 지역 조회
	List<Like> getLikeRegion(int user_idx) throws SQLException;

	// 관심 지역 삭제
	int deleteLikeRegion(int like_idx) throws SQLException;
}
