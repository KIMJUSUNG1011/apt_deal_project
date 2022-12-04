package com.ssafy.home.controller;

import java.sql.SQLException;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import com.ssafy.home.dto.Like;
import com.ssafy.home.dto.User;
import com.ssafy.home.model.service.LikeService;

import lombok.extern.slf4j.Slf4j;

@Controller
@RequestMapping(value = "/like")
@Slf4j
public class LikeController {

	@Autowired
	LikeService likeService;
	
	@GetMapping("/goLikeList")
	public String goLikeList() {
		return "/homeInfo/likeapt";
	}

	@PostMapping
	public ResponseEntity<String> add(HttpSession session, @RequestBody Like like) throws SQLException {
		User user = (User) session.getAttribute("userInfo");
		int cnt = likeService.registLikeApt(user.getIdx(), like.getSidoName(), like.getGugunName(), like.getDongName());
		if (cnt == 1) {
			log.debug("관심 지역 등록 성공");
			return new ResponseEntity<String>("관심 지역 등록 성공", HttpStatus.OK);
		} else {
			log.debug("관심 지역 등록 실패");
			return new ResponseEntity<String>("관심 지역 등록 실패", HttpStatus.NOT_MODIFIED);
		}
	}
	
	@GetMapping
	public ResponseEntity<List<Like>> selectLike(HttpSession session) throws SQLException {
		User user = (User) session.getAttribute("userInfo");
		List<Like> likeList = likeService.getLikeRegion(user.getIdx());
		for (Like a : likeList)
			System.out.println("시 : " + a.getSidoName());
		return new ResponseEntity<List<Like>>(likeList, HttpStatus.OK);
	}
}
