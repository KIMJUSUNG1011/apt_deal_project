package com.ssafy.home.model.service;

import java.sql.SQLException;
import java.util.List;

import com.ssafy.home.dto.Apt;
import com.ssafy.home.util.PagingVO;

public interface AptService {

	// 시도, 구군, 동 정보가 전달되면 해당하는 동 코드를 리턴
	String getDongCode(String sidoName, String gugunName, String dongName) throws SQLException;

	// 시도 정보
	List<String> getSidoName() throws SQLException;

	// 구군 정보 가져오기
	List<String> getGugunName(String sidoName) throws SQLException;

	// 동 정보 가져오기
	List<String> getDongName(String sidoName, String gugunName) throws SQLException;

	// 아파트 상세정보 총 개수
	int countApt(String sidoName, String gugunName, String dongName) throws SQLException ;

	// 아파트 상세 조회 리스트
	List<Apt> getAptDetail(PagingVO vo, String sidoName, String gugunName, String dongName) throws SQLException;
	List<Apt> getAptDetail2(PagingVO vo, String sidoName, String gugunName, String dongName, String apartmentName) throws SQLException;

	// 특정 아파트의 거래 내역 총 개수
	int countAptDeal(String aptCode);

	// 아파트 거래 내역 조회 리스트
	List<Apt> getAptDeal(PagingVO vo, String aptCode);

	// 크롤링
	void crawling(String apartmentName, String img);
}
