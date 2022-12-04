package com.ssafy.home.controller;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.ssafy.home.dto.Notice;
import com.ssafy.home.dto.User;
import com.ssafy.home.dto.User_Notice;
import com.ssafy.home.model.service.NoticeService;
import com.ssafy.home.util.PagingVO;

import lombok.extern.slf4j.Slf4j;

@Controller
@RequestMapping(value = "/notice")
@Slf4j
public class NoticeController {

	@Autowired
	NoticeService noticeService;

	@GetMapping("/goList")
	public String goList() {
		return "/include/nav/noticeSelectAll";
	}

	@GetMapping("/goDetail/{noticeIdx}")
	public String goDetail() {
		return "/include/nav/noticeSelect";
	}

	@PostMapping
	public ResponseEntity<String> add(@RequestBody Notice notice) throws SQLException {
		
		int cnt = noticeService.add(notice);
		if (cnt == 1) {
			log.debug("공지사항 등록 성공");
			return new ResponseEntity<String>("공지사항 등록 성공", HttpStatus.OK);
		} else {
			log.debug("공지사항 등록 실패");
			return new ResponseEntity<String>("공지사항 등록 실패", HttpStatus.NOT_MODIFIED);
		}
	}

	@PutMapping("/{noticeIdx}")
	public ResponseEntity<String> edit(@RequestBody Notice notice,
			@PathVariable("noticeIdx") int noticeIdx) throws SQLException {

		// 로그인이 안되어 있는 경우
		// 다른 사람의 게시글에 대한 수정 버튼을 클릭한 경우
		// 인터셉터에서 설정

		notice.setNoticeIdx(noticeIdx);
		int cnt = noticeService.edit(notice);
		if (cnt == 1) {
			log.debug("공지사항 수정 성공");
			return new ResponseEntity<String>("공지사항 수정 성공", HttpStatus.OK);
		} else {
			log.debug("공지사항 수정 실패");
			return new ResponseEntity<String>("공지사항 등록 실패", HttpStatus.NOT_ACCEPTABLE);
		}
	}

	@DeleteMapping("/{noticeIdx}")
	public ResponseEntity<String> delete(@PathVariable("noticeIdx") int noticeIdx)
			throws SQLException {

		// 로그인이 안되어 있는 경우
		// 다른 사람의 게시글에 대한 삭제 버튼을 클릭한 경우
		// 인터셉터에서 설정

		int cnt = noticeService.delete(noticeIdx);
		if (cnt == 1) {
			log.debug("공지사항 삭제 성공");
			return new ResponseEntity<String>("공지사항 삭제 성공", HttpStatus.OK);
		} else {
			log.debug("공지사항 삭제 실패");
			return new ResponseEntity<String>("공지사항 삭제 실패", HttpStatus.NOT_ACCEPTABLE);
		}
	}

	@GetMapping("/list")
	public ResponseEntity<Map<String, Object>> list(PagingVO vo,
			@RequestParam(value = "nowPage", required = false) String nowPage,
			@RequestParam(value = "cntPerPage", required = false) String cntPerPage) throws SQLException {

		int total = noticeService.countNotice();
		
		vo = new PagingVO(total, Integer.parseInt(nowPage), Integer.parseInt(cntPerPage));
		vo.setEnd(vo.getEnd() - vo.getStart() + 1);
		vo.setStart(vo.getStart() - 1);
	
		Map<String, Object> map = new HashMap<>();
		map.put("paging", vo);
		map.put("viewAll", noticeService.list(vo));
		return new ResponseEntity<Map<String, Object>>(map, HttpStatus.OK);
	}

	@GetMapping("/getDetail/{noticeIdx}")
	public ResponseEntity<User_Notice> view(@PathVariable("noticeIdx") int noticeIdx) throws SQLException {
		User_Notice user_notice = noticeService.view(noticeIdx);
		
		return new ResponseEntity<User_Notice>(user_notice, HttpStatus.OK);
	}
}
