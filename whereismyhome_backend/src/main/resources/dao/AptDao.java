package com.ssafy.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.ssafy.dto.AptDTO;
import com.ssafy.util.DBUtil;

public class AptDao {

	private AptDao() {
	}

	private static AptDao instance = new AptDao();

	public static AptDao getInstance() {
		return instance;
	}

	private DBUtil dbUtil = DBUtil.getInstance();

	// 시도, 구군, 동 정보가 전달되면 해당하는 동 코드를 리턴
	public String getDongCode(String sidoName, String gugunName, String dongName) throws SQLException {

		String sql = "select dongCode from dongcode where sidoName=? and gugunName=? and dongName=?\n";
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
			conn = dbUtil.getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, sidoName);
			pstmt.setString(2, gugunName);
			pstmt.setString(3, dongName);
			rs = pstmt.executeQuery();
			if (rs.next()) {
				return rs.getString("dongCode");
			}
		} finally {
			dbUtil.close(rs, pstmt, conn);
		}

		return null;
	}

	// 시도 정보
	public List<String> getSidoName() throws SQLException {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql = "select sidoName from dongcode where right(dongcode, 8) = '00000000'";

		try {
			conn = dbUtil.getConnection();
			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();
			List<String> sidoList = new ArrayList<>();
			while (rs.next()) {
				sidoList.add(rs.getString("sidoName"));
			}
			return sidoList;
		} finally {
			dbUtil.close(rs, pstmt, conn);
		}
	}

	// 구군 정보 가져오기
	public List<String> getGugunName(String sidoName) throws SQLException {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql = "select gugunName from dongcode where sidoName = ? and right(dongcode, 5)='00000' and gugunName is not null";

		try {
			conn = dbUtil.getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, sidoName);
			rs = pstmt.executeQuery();
			List<String> gugunList = new ArrayList<>();
			while (rs.next()) {
				gugunList.add(rs.getString("gugunName"));
			}
			return gugunList;
		} finally {
			dbUtil.close(rs, pstmt, conn);
		}
	}

	// 동 정보 가져오기
	public List<String> getDongName(String sidoName, String gugunName) throws SQLException {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql = "select dongName from dongcode where sidoName = ? and gugunName = ? and dongName is not null";

		try {
			conn = dbUtil.getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, sidoName);
			pstmt.setString(2, gugunName);
			rs = pstmt.executeQuery();
			List<String> dongList = new ArrayList<>();
			while (rs.next()) {
				dongList.add(rs.getString("dongName"));
			}
			return dongList;
		} finally {
			dbUtil.close(rs, pstmt, conn);
		}
	}

	// 아파트 실거래가 조회 리스트
	public List<AptDTO> getAptDealList(String sidoName, String gugunName, String dongName) throws SQLException {

		String dongCode = getDongCode(sidoName, gugunName, dongName);
		String sql = "select B.apartmentName, A.dealAmount, A.area, A.dealYear, A.dealMonth, A.dealDay, B.lng, B.lat\r\n"
				+ "from housedeal A, houseinfo B\r\n" + "where B.dongCode = ? and A.aptCode = B.aptCode\n";
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		List<AptDTO> list = new ArrayList<>();

		try {
			conn = dbUtil.getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, dongCode);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				AptDTO apt = new AptDTO();
				apt.setApartmentName(rs.getString("apartmentName"));
				apt.setDealAmount(rs.getString("dealAmount"));
				apt.setArea(rs.getString("area"));
				String dealYear = String.valueOf(rs.getInt("dealYear"));
				String dealMonth = String.valueOf(rs.getInt("dealMonth"));
				String dealDay = String.valueOf(rs.getInt("dealDay"));
				apt.setDealDate(dealYear + "." + dealMonth + "." + dealDay);
				apt.setLng(rs.getString("lng"));
				apt.setLat(rs.getString("lat"));
				list.add(apt);
			}
			return list;
		} finally {
			dbUtil.close(rs, pstmt, conn);
		}
	}

	// 아파트 실거래가 조회 리스트(월,일 추가)
	public List<AptDTO> getAptDealList(String sidoName, String gugunName, String dongName, String dealYear,
			String dealMonth) throws SQLException {

		String dongCode = getDongCode(sidoName, gugunName, dongName);
		String sql = "select B.apartmentName, A.dealAmount, A.area, A.dealYear, A.dealMonth, A.dealDay, B.lng, B.lat\r\n"
				+ "from housedeal A, houseinfo B\r\n"
				+ "where B.dongCode = ? and A.aptCode = B.aptCode and A.dealYear = ? and A.dealMonth = ?\n";
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		List<AptDTO> list = new ArrayList<>();

		try {
			conn = dbUtil.getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, dongCode);
			pstmt.setString(2, dealYear);
			pstmt.setString(3, dealMonth);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				AptDTO apt = new AptDTO();
				apt.setApartmentName(rs.getString("apartmentName"));
				apt.setDealAmount(rs.getString("dealAmount"));
				apt.setArea(rs.getString("area"));
				String dealDay = String.valueOf(rs.getInt("dealDay"));
				apt.setDealDate(dealYear + "." + dealMonth + "." + dealDay);
				apt.setLng(rs.getString("lng"));
				apt.setLat(rs.getString("lat"));
				list.add(apt);
			}
			return list;
		} finally {
			dbUtil.close(rs, pstmt, conn);
		}
	}

	// 아파트 상세 조회
	public List<AptDTO> getAptDetail(String sidoName, String gugunName, String dongName) throws SQLException {
		String dongCode = getDongCode(sidoName, gugunName, dongName);
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql = "SELECT apartmentName, roadName, buildYear, lng, lat FROM houseinfo where dongcode = ?";

		try {
			conn = dbUtil.getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, dongCode);
			rs = pstmt.executeQuery();
			List<AptDTO> aptList = new ArrayList<>();
			while (rs.next()) {
				String apartmentName = rs.getString("apartmentName");
				String roadName = rs.getString("roadName");
				String buildYear = String.valueOf(rs.getInt("buildYear"));
				String lng = rs.getString("lng");
				String lat = rs.getString("lat");
				AptDTO apt = new AptDTO(apartmentName, lng, lat, roadName, buildYear);
				aptList.add(apt);
			}
			return aptList;
		} finally {
			dbUtil.close(rs, pstmt, conn);
		}
	} 
	
	
}
