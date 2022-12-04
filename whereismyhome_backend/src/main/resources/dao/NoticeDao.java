package com.ssafy.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.ssafy.dto.NoticeDTO;
import com.ssafy.dto.User_Notice_DTO;
import com.ssafy.util.DBUtil;

public class NoticeDao {

	// 싱글톤
	private NoticeDao() {
	}

	private static NoticeDao instance = new NoticeDao();

	public static NoticeDao getInstance() {
		return instance;
	}

	private DBUtil dbUtil = DBUtil.getInstance();

	// 공지사항 등록
	public int add(NoticeDTO noticeDTO) throws SQLException {
		String sql = "INSERT INTO notice (user_idx, title, content) VALUES (?,?,?)";
		Connection conn = null;
		PreparedStatement pstmt = null;
		try {
			conn = dbUtil.getConnection();
			pstmt = conn.prepareStatement(sql);
			int cnt = 0;
			pstmt.setInt(++cnt, noticeDTO.getUserIdx());
			pstmt.setString(++cnt, noticeDTO.getTitle());
			pstmt.setString(++cnt, noticeDTO.getContent());
			int res = pstmt.executeUpdate();
			return res;
			
		} finally {
			dbUtil.close(pstmt, conn);
		}
	}

	// 공지사항 수정
	public int edit(NoticeDTO noticeDTO) throws SQLException {
		String sql = "UPDATE notice SET title = ?, content = ? WHERE notice_idx=?";
		Connection conn = null;
		PreparedStatement pstmt = null;
		try {
			conn = dbUtil.getConnection();
			pstmt = conn.prepareStatement(sql);
			int cnt = 0; 
			pstmt.setString(++cnt, noticeDTO.getTitle());
			pstmt.setString(++cnt, noticeDTO.getContent());
			pstmt.setInt(++cnt, noticeDTO.getNoticeIdx());
			int res = pstmt.executeUpdate();
			return res;
		} finally {
			dbUtil.close(pstmt, conn);
		}
	}

	// 공지사항 삭제
	public int delete(int noticeIdx) throws SQLException {
		String sql = "DELETE FROM notice WHERE notice_idx = ?";
		Connection conn = null;
		PreparedStatement pstmt = null;
		try {
			conn = dbUtil.getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, noticeIdx);
			int res = pstmt.executeUpdate();
			return res;
		} finally {
			dbUtil.close(pstmt, conn);
		}
	}

	// 공지사항 목록 보기
	public List<User_Notice_DTO> list() throws SQLException {
		String sql = "SELECT A.notice_idx, A.user_idx, A.title, A.content, A.registerDate, B.name FROM notice A, user B where A.user_idx = B.idx\n;";
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			conn = dbUtil.getConnection();
			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();
			List<User_Notice_DTO> list = new ArrayList<>();
			while(rs.next()) {
				int notice_idx = rs.getInt("notice_idx");
				int user_idx = rs.getInt("user_idx");
				String title = rs.getString("title");
				String content = rs.getString("content");
				String registerDate = rs.getString("registerDate");
				String name = rs.getString("name");
				User_Notice_DTO user = new User_Notice_DTO(notice_idx, user_idx, title, content, registerDate, name);
				list.add(user);
			}
			return list;
		} finally {
			dbUtil.close(pstmt, conn);
		}
	}

	// 공지사항 상세 보기
	public User_Notice_DTO view(int noticeIdx) throws SQLException {
		String sql = "SELECT A.notice_idx, A.user_idx, A.title, A.content, A.registerDate, B.name FROM notice A, user B WHERE A.user_idx = B.idx AND notice_idx = ?";
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			User_Notice_DTO user = null;
			conn = dbUtil.getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, noticeIdx);
			rs = pstmt.executeQuery();
			if(rs.next()) {
				int notice_idx = rs.getInt("notice_idx");
				int user_idx = rs.getInt("user_idx");
				String title = rs.getString("title");
				String content = rs.getString("content");
				String registerDate = rs.getString("registerDate");
				String name = rs.getString("name");
				user = new User_Notice_DTO(notice_idx, user_idx, title, content, registerDate, name);
			}
			return user;
		} finally {
			dbUtil.close(pstmt, conn);
		}
	}
}
