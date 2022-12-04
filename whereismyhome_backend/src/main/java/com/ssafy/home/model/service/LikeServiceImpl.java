package com.ssafy.home.model.service;

import java.sql.SQLException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ssafy.home.dto.Like;
import com.ssafy.home.model.mapper.LikeMapper;

@Service
public class LikeServiceImpl implements LikeService {
	
	@Autowired
	LikeMapper likeMapper;

	@Override
	public int registLikeApt(int user_idx, String sidoName, String gugunName, String dongName) throws SQLException {
		return likeMapper.registLikeApt(user_idx, sidoName, gugunName, dongName);
	}

	@Override
	public List<Like> getLikeRegion(int user_idx) throws SQLException {
		return likeMapper.getLikeRegion(user_idx);
	}

	@Override
	public int deleteLikeRegion(int like_idx) throws SQLException {
		return likeMapper.deleteLikeRegion(like_idx);
	}

}
