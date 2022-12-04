package com.ssafy.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.ssafy.dto.LikeDTO;
import com.ssafy.util.DBUtil;

public class LikeDao {
	// 싱글톤
	private LikeDao() {}
	private static LikeDao instance = new LikeDao();
	public static LikeDao getInstance() {
		return instance;
	}
	
	private DBUtil dbUtil = DBUtil.getInstance();

	// 관심 지역 등록
	public int registLikeApt(int user_idx, String sidoName, String gugunName, String dongName) throws SQLException {
		Connection conn = null;
		PreparedStatement pstmt = null;
		
		String sql = "INSERT INTO likeapt (user_idx, sidoName, gugunName, dongName) VALUES(?, ?, ?, ?)";
		
		try {
			conn = dbUtil.getConnection();
			pstmt = conn.prepareStatement(sql);
			int cnt = 0;
			pstmt.setInt(++cnt, user_idx);
			pstmt.setString(++cnt, sidoName);
			pstmt.setString(++cnt, gugunName);
			pstmt.setString(++cnt, dongName);
			int res = pstmt.executeUpdate();
			return res;
		} finally {
			dbUtil.close(pstmt, conn);
		}
	}
	
	// 관심 지역 조회
	public List<LikeDTO> getLikeRegion(int user_idx) throws SQLException{
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql = "SELECT like_idx, sidoName, gugunName, dongName FROM likeapt WHERE user_idx = ?";

		try {
			conn = dbUtil.getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, user_idx);
			rs = pstmt.executeQuery();
			List<LikeDTO> likeList = new ArrayList<>();
			while (rs.next()) {
				int like_idx = rs.getInt("like_idx");
				String sidoName = rs.getString("sidoName");
				String gugunName = rs.getString("gugunName");
				String dongName = rs.getString("dongName");
				LikeDTO like = new LikeDTO(like_idx, sidoName, gugunName, dongName);
				likeList.add(like);
			}
			return likeList;
		} finally {
			dbUtil.close(rs, pstmt, conn);
		}
	}
	
	// 관심 지역 삭제
	public int deleteLikeRegion(int like_idx) throws SQLException {
		Connection conn = null;
		PreparedStatement pstmt = null;
		
		String sql = "DELETE FROM likeapt WHERE like_idx = ?";
		try {
			conn = dbUtil.getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, like_idx);
			int res = pstmt.executeUpdate();
			return res;
		} finally {
			dbUtil.close(pstmt, conn);
		}
	}
}
