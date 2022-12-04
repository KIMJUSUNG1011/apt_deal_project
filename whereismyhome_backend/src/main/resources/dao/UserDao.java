package com.ssafy.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.ssafy.dto.UserDTO;
import com.ssafy.util.DBUtil;

public class UserDao {

	// 싱글톤
	private UserDao() {
	}

	private static UserDao instance = new UserDao();

	public static UserDao getInstance() {
		return instance;
	}

	private DBUtil dbUtil = DBUtil.getInstance();

	// 회원 가입
	public UserDTO add(UserDTO user) throws SQLException {
		Connection conn = null;
		PreparedStatement pstmt = null;
		String sql = "INSERT INTO user (userId, pass, name, phone, img) VALUES (?,?,?,?,?)";
		try {
			conn = dbUtil.getConnection();
			pstmt = conn.prepareStatement(sql);
			int cnt = 0;
			pstmt.setString(++cnt, user.getUserId());
			pstmt.setString(++cnt, user.getPass());
			pstmt.setString(++cnt, user.getName());
			pstmt.setString(++cnt, user.getPhone());
			pstmt.setString(++cnt, user.getImg());
			int res = pstmt.executeUpdate();
			if (res == 1) {
				return user;
			} else {
				return null;
			}
		} finally {
			dbUtil.close(pstmt, conn);
		}
	}

	// 로그인
	public UserDTO login(String id, String pass) throws SQLException {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql = "SELECT idx, userId, name, img from user where userId = ? AND pass = ?";
		UserDTO userInfo = null;

		try {
			conn = dbUtil.getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, id);
			pstmt.setString(2, pass);
			rs = pstmt.executeQuery();

			if (rs.next()) {
				userInfo = new UserDTO();
				userInfo.setIdx(rs.getInt("idx"));
				userInfo.setUserId(rs.getString("userId"));
				userInfo.setName(rs.getString("name"));
				userInfo.setImg(rs.getString("img"));
			}
		} finally {
			dbUtil.close(rs, pstmt, conn);
		}
		return userInfo;
	}

	// 회원 정보 수정 기능
	public int edit(UserDTO user) throws SQLException {

		String sql = "update user set pass=?,name=?,phone=?,img=? where idx=?\n";

		Connection conn = null;
		PreparedStatement pstmt = null;
		int cnt = 0;

		try {
			conn = dbUtil.getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, user.getPass());
			pstmt.setString(2, user.getName());
			pstmt.setString(3, user.getPhone());
			pstmt.setString(4, user.getImg());
			pstmt.setInt(5, user.getIdx());
			cnt = pstmt.executeUpdate();
			return cnt;
		} finally {
			dbUtil.close(pstmt, conn);
		}
	}

	// 회원 탈퇴
	public int delete(String id, String pass) throws SQLException {

		String sql = "update user set withdrawalDate = now() where userId=? AND pass=?\n";

		Connection conn = null;
		PreparedStatement pstmt = null;
		int cnt = 0;

		try {
			conn = dbUtil.getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, id);
			pstmt.setString(2, pass);
			cnt = pstmt.executeUpdate();
			return cnt;
		} finally {
			dbUtil.close(pstmt, conn);
		}
	}

	// 회원 정보 조회 마이페이지
	public UserDTO view(int id) throws SQLException {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql = "SELECT userId, name, phone, registerDate, img from user where idx = ?";
		UserDTO userInfo = null;

		try {
			conn = dbUtil.getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, id);
			rs = pstmt.executeQuery();

			if (rs.next()) {
				userInfo = new UserDTO();
				userInfo.setUserId(rs.getString("userId"));
				userInfo.setName(rs.getString("name"));
				userInfo.setPhone(rs.getString("phone"));
				userInfo.setRegisterDate(rs.getString("registerDate"));
				userInfo.setImg(rs.getString("img"));
			}
		} finally {
			dbUtil.close(rs, pstmt, conn);
		}
		return userInfo;
	}
}
