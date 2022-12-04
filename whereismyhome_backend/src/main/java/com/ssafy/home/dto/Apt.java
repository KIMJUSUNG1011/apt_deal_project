package com.ssafy.home.dto;

import lombok.Data;

@Data
public class Apt {
	
	// 아파트 식별 코드
	private String aptCode;			// 아파트 식별 코드

	// 공통 정보
	private String apartmentName; 	// 아파트 이름
	private String lng;				// 위도
	private String lat;				// 경도
	
	// 거래 정보
	private String dealAmount;		// 거래 금액
	private String area;			// 면적
	private String dealYear;
	private String dealMonth;
	private String dealDay;
	
	// 상세 정보
	private String roadName;		// 길 이름
	private String buildYear;		// 건축년도
	private String img;				// 이미지 경로
	private String floor;
	private String jibun;
}
