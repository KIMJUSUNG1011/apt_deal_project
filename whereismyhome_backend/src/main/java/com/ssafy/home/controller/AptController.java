package com.ssafy.home.controller;

import java.io.IOException;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.ssafy.home.dto.Apt;
import com.ssafy.home.model.service.AptService;
import com.ssafy.home.util.PagingVO;

import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping(value = "/apt")
@Slf4j
public class AptController {

	@Autowired
	AptService aptService;

	@GetMapping("/selectSido")
	public List<String> selectSido() throws SQLException, IOException {
		return aptService.getSidoName();
	}

	@GetMapping("/selectGugun/{sido}")
	public List<String> selectGugun(@PathVariable("sido") String sido) throws SQLException {
		return aptService.getGugunName(sido);
	}

	@GetMapping("/selectDong/{sido}/{gugun}")
	public List<String> selectDong(@PathVariable("sido") String sido, @PathVariable("gugun") String gugun)
			throws SQLException {
		return aptService.getDongName(sido, gugun);
	}

	@GetMapping("/selectAptDetail/{sido}/{gugun}/{dong}")
	public ResponseEntity<Map<String, Object>> selectAptDetail(PagingVO vo,
			@RequestParam(value = "nowPage", required = false) String nowPage,
			@RequestParam(value = "cntPerPage", required = false) String cntPerPage,
			@PathVariable("sido") String sido, @PathVariable("gugun") String gugun,
			@PathVariable("dong") String dong) throws SQLException {

		int total = aptService.countApt(sido, gugun, dong);
		
		vo = new PagingVO(total, Integer.parseInt(nowPage), Integer.parseInt(cntPerPage));
		vo.setEnd(vo.getEnd() - vo.getStart() + 1);
		vo.setStart(vo.getStart() - 1);
	
		Map<String, Object> map = new HashMap<>();
		map.put("paging", vo);
		map.put("viewAll", aptService.getAptDetail(vo, sido, gugun, dong));
		return new ResponseEntity<Map<String, Object>>(map, HttpStatus.OK);
	}

	@GetMapping("/selectAptDetail2/{sido}/{gugun}/{dong}/{apartmentName}")
	public ResponseEntity<Map<String, Object>> selectAptDetail2(PagingVO vo,
			@RequestParam(value = "nowPage", required = false) String nowPage,
			@RequestParam(value = "cntPerPage", required = false) String cntPerPage,
			@PathVariable("sido") String sido, @PathVariable("gugun") String gugun,
			@PathVariable("dong") String dong, @PathVariable("apartmentName") String apartmentName) throws SQLException {

		int total = aptService.countApt(sido, gugun, dong);
		
		vo = new PagingVO(total, Integer.parseInt(nowPage), Integer.parseInt(cntPerPage));
		vo.setEnd(vo.getEnd() - vo.getStart() + 1);
		vo.setStart(vo.getStart() - 1);
	
		Map<String, Object> map = new HashMap<>();
		map.put("paging", vo);
		map.put("viewAll", aptService.getAptDetail2(vo, sido, gugun, dong,apartmentName));
		return new ResponseEntity<Map<String, Object>>(map, HttpStatus.OK);
	}
	
	@GetMapping("/dealList/{aptCode}")
	public ResponseEntity<Map<String, Object>> selectAptDeal(PagingVO vo,
			@RequestParam(value = "nowPage", required = false) String nowPage,
			@RequestParam(value = "cntPerPage", required = false) String cntPerPage,
			@PathVariable("aptCode") String aptCode
			) {
		
		int total = aptService.countAptDeal(aptCode);

		vo = new PagingVO(total, Integer.parseInt(nowPage), Integer.parseInt(cntPerPage));
		vo.setEnd(vo.getEnd() - vo.getStart() + 1);
		vo.setStart(vo.getStart() - 1);
	
		Map<String, Object> map = new HashMap<>();
		map.put("paging", vo);
		map.put("viewAll", aptService.getAptDeal(vo, aptCode));
		return new ResponseEntity<Map<String, Object>>(map, HttpStatus.OK);
	}

	// 웹 크롤링을 통해 houseinfo 테이블에 img 컬럼을 채우는 메소드
	@PostMapping("/crawling")
	public void crawling(@RequestBody Apt apt) {
		log.debug(apt.toString());
		aptService.crawling(apt.getApartmentName(), apt.getImg());
	}

}
