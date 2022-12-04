package com.ssafy.home.model.service;

import java.sql.SQLException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ssafy.home.dto.Apt;
import com.ssafy.home.model.mapper.AptMapper;
import com.ssafy.home.util.PagingVO;

@Service
public class AptServiceImpl implements AptService {
	
	@Autowired
	AptMapper aptMapper;

	@Override
	public String getDongCode(String sidoName, String gugunName, String dongName) throws SQLException {
		return aptMapper.getDongCode(sidoName, gugunName, dongName);
	}

	@Override
	public List<String> getSidoName() throws SQLException {
		return aptMapper.getSidoName();
	}

	@Override
	public List<String> getGugunName(String sidoName) throws SQLException {
		return aptMapper.getGugunName(sidoName);
	}

	@Override
	public List<String> getDongName(String sidoName, String gugunName) throws SQLException {
		return aptMapper.getDongName(sidoName, gugunName);
	}

	// 아파트 상세정보 총 개수
	@Override
	public int countApt(String sidoName, String gugunName, String dongName) throws SQLException {
		String dongCode = getDongCode(sidoName, gugunName, dongName);
		return aptMapper.countApt(dongCode);
	}

	@Override
	public List<Apt> getAptDetail(PagingVO vo, String sidoName, String gugunName, String dongName) throws SQLException {
		String dongCode = getDongCode(sidoName, gugunName, dongName);
		return aptMapper.getAptDetail(dongCode, vo);
	}

	@Override
	public void crawling(String apartmentName, String img) {
		aptMapper.crawling(apartmentName, img);
	}

	@Override
	public List<Apt> getAptDetail2(PagingVO vo, String sidoName, String gugunName, String dongName,
			String apartmentName) throws SQLException {
		String dongCode = getDongCode(sidoName, gugunName, dongName);
		return aptMapper.getAptDetail2(dongCode, vo, apartmentName);
	}

	@Override
	public int countAptDeal(String aptCode) {
		return aptMapper.countAptDeal(aptCode);
	}

	@Override
	public List<Apt> getAptDeal(PagingVO vo, String aptCode) {
		return aptMapper.getAptDeal(aptCode, vo);
	}

}
