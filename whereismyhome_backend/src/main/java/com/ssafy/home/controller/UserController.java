package com.ssafy.home.controller;

import java.io.File;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.sql.SQLException;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import javax.mail.MessagingException;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.xml.ws.http.HTTPBinding;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.ssafy.home.dto.EmailData;
import com.ssafy.home.dto.FileInfo;
import com.ssafy.home.dto.User;
import com.ssafy.home.model.service.UserService;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

@Controller
@RequestMapping("/user")
public class UserController {
	final static int EXPIRE_MINUTES = 60;
	final static String SECRET_KEY="ssafy";
	private final Logger logger = LoggerFactory.getLogger(UserController.class);

	private UserService userService;

	@Autowired
	public UserController(UserService userService) {
		super();
		this.userService = userService;
	}
	
	@PostMapping("/idCheck")
	public ResponseEntity<?> idCheck(@RequestBody User user, Model model) throws SQLException {
		int cnt = userService.idCheck(user);
		return new ResponseEntity<Integer>(cnt, HttpStatus.OK);
	}

	// 회원가입
	@PostMapping
	@ResponseBody
	public ResponseEntity<?> add(@RequestBody User user, Model model) throws SQLException {
		logger.debug("user info : {}", user);

		userService.add(user);

		return new ResponseEntity<Void>(HttpStatus.OK);
	}

	// 로그인
	@GetMapping("/login")
	@ResponseBody
	public ResponseEntity<?> login(User luser, Model model, HttpSession session, HttpServletResponse response)
			throws SQLException, UnsupportedEncodingException {
		logger.debug("map : {}", luser.getUserId());

		User user = userService.login(luser.getUserId(), luser.getPass());
		logger.debug("로그인성공아이디 : {}", user.getUserId());
		if (user != null) {
			String token = Jwts.builder()
					//header
					.setHeaderParam("typ", "JWT")
					.setHeaderParam("alg", "HS256")
					//payload
					.claim("idx", user.getIdx())
					.claim("id", user.getUserId())
					.claim("name", user.getName())
					.claim("phone", user.getPhone())
					.claim("regdate",user.getRegisterDate())
					.claim("img",user.getImg())
					.setExpiration(new Date(System.currentTimeMillis()+1000*60*EXPIRE_MINUTES))
					//signature
					.signWith(SignatureAlgorithm.HS256, SECRET_KEY.getBytes("UTF-8"))
					.compact();
			logger.debug("발급된 토큰 : {}",token);
			
			Map<String,String> result = new HashMap<>();
			result.put("token", token);
			
			return new ResponseEntity<Map<String,String>>(result,HttpStatus.OK);
		} else {
			model.addAttribute("msg", "아이디 또는 비밀번호 확인 후 다시 로그인하세요!");
			return new ResponseEntity<User>(user,HttpStatus.OK);
		}
	}
	@GetMapping("/getUser")
	@ResponseBody
	public ResponseEntity<?> getUser(User user,Model model, HttpSession session){
		
		User cUser = (User) session.getAttribute("userInfo");
		
		return new ResponseEntity<User>(cUser,HttpStatus.OK);
		
	}
	// 로그아웃
	@GetMapping("/logout")
	public String logout(HttpSession session) {
		session.invalidate();
		return "index";
	}

	// 마이페이지 이동
	@GetMapping("/mypage")
	public String mypage(HttpSession session) {
		return "/include/nav/mypage";
	}

	// 회원수정
	@PutMapping
	@ResponseBody
	public ResponseEntity<?> edit(@RequestBody User user, Model model, HttpSession session) throws SQLException {
		logger.debug("edit user info : {}", user);

		userService.edit(user);
		User nUser = userService.login(user.getUserId(), user.getPass());
		session.setAttribute("userInfo", nUser);

		return new ResponseEntity<Void>(HttpStatus.OK);
	}

	// 비밀 번호 찾기
	@ResponseBody
	@GetMapping("/findPass")
	public ResponseEntity<?> findPass(User user, Model model, HttpSession session) throws SQLException {
		logger.debug("find user info : {}", user);

		// 1. 비밀번호 업데이트 -> 랜덤한 난수
		String newPass = userService.makeRandomPass(10);
		// 2. nUser 에 새로운 비밀번호 세팅 후 전송
		User newUser = new User();
		newUser.setPass(newPass);
		newUser.setUserId(user.getUserId());
		userService.edit(newUser);
		return new ResponseEntity<String>(newUser.getPass(), HttpStatus.OK);
	}

	// 회원탈퇴
	@DeleteMapping
	@ResponseBody
	public ResponseEntity<?> delete(@RequestBody User user, Model model, HttpSession session) throws SQLException {
		logger.debug("delete user info : {}", user);
		userService.delete(user.getUserId(), user.getPass());
		session.invalidate();

		return new ResponseEntity<Void>(HttpStatus.OK);
	}
	
	// 이미지 업로드
	@PostMapping("/setImg")
	@ResponseBody
	public ResponseEntity<?> setImg(User user,@RequestParam("file") MultipartFile file,Model model,HttpSession session) throws SQLException, IllegalStateException, IOException{
		logger.debug("set img info:{}",user);
		String path = "C:\\ssafy\\view_workspace\\whereismyhome\\template_test\\public\\assets\\img\\user\\";
		File folder = new File(path);
		String originFile = file.getOriginalFilename();
		String saveFile = UUID.randomUUID().toString()+originFile.substring(originFile.lastIndexOf('.'));
		FileInfo fileInfo = new FileInfo();
		fileInfo.setUserId(user.getUserId());
		fileInfo.setSaveFile(saveFile);
		fileInfo.setOriginFile(originFile);
		fileInfo.setSaveFolder("assets/img/user/");
		file.transferTo(new File(folder, saveFile));
		user.setFileInfo(fileInfo);
		user.setImg(fileInfo.getSaveFolder()+fileInfo.getSaveFile());
		userService.insertImg(user);
		userService.setUserImg(user);
		
		return new ResponseEntity<User>(user,HttpStatus.OK);
	}
	
	@PostMapping("/findPassEmail")
	public void sendPassEmail(@RequestBody EmailData emailData) throws UnsupportedEncodingException, MessagingException {
		logger.debug("이메일 요청이 왔습니다. {}", emailData);
		userService.sendPassEmail(emailData);
	}
	
	@PostMapping("/email")
	public void sendEmail(@RequestBody EmailData emailData) throws UnsupportedEncodingException, MessagingException {
		logger.debug("이메일 요청이 왔습니다. {}", emailData);
		userService.sendEmail(emailData);
	}

}
