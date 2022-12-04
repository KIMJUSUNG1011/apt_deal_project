package com.ssafy.home.model.mapper;

import java.sql.SQLException;
import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.ssafy.home.dto.Apt;
import com.ssafy.home.util.PagingVO;

@Mapper
public interface AptMapper {

	// 시도, 구군, 동 정보가 전달되면 해당하는 동 코드를 리턴
	String getDongCode(String sidoName, String gugunName, String dongName) throws SQLException;

	// 시도 정보
	List<String> getSidoName() throws SQLException;

	// 구군 정보 가져오기
	List<String> getGugunName(String sidoName) throws SQLException;

	// 동 정보 가져오기
	List<String> getDongName(String sidoName, String gugunName) throws SQLException;
	
	// 아파트 매물 총 갯수
	int countApt(String dongCode);

	// 아파트 상세 조회
	List<Apt> getAptDetail(String dongCode, PagingVO vo) throws SQLException;
	List<Apt> getAptDetail2(String dongCode, PagingVO vo, String apartmentName) throws SQLException;

	// 특정 아파트의 거래 내역 총 개수
	int countAptDeal(String aptCode);

	// 아파트 거래 내역 조회 리스트
	List<Apt> getAptDeal(String aptCode, PagingVO vo);

	// 크롤링
	void crawling(String apartmentName, String img);

}
