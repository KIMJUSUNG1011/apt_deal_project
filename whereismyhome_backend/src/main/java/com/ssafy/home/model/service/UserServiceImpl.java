package com.ssafy.home.model.service;

import java.io.UnsupportedEncodingException;
import java.security.SecureRandom;
import java.sql.SQLException;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import com.ssafy.home.dto.EmailData;
import com.ssafy.home.dto.User;
import com.ssafy.home.model.mapper.UserMapper;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {
	
	private final JavaMailSender mailSender;
    private static final String ADMIN_ADDRESS = "dlrtls12345@naver.com";

    @Autowired
	UserMapper userMapper;

	@Override
	public void add(User user) throws SQLException {
		userMapper.add(user);
	}

	@Override
	public User login(String userId, String pass) throws SQLException {
		return userMapper.login(userId, pass);
	}

	@Override
	public User view(int id) throws SQLException {
		return userMapper.view(id);
	}

	@Override
	public int edit(User user) throws SQLException {
		return userMapper.edit(user);
	}

	@Override
	public int delete(String userId, String pass) throws SQLException {
		return userMapper.delete(userId, pass);
	}

	@Override
	public User findPass(String userId, String name) throws SQLException {
		return userMapper.findPass(userId, name);
	}

	@Override
	public void insertImg(User user) throws SQLException {
		userMapper.insertImg(user);
	}

	@Override
	public void setUserImg(User user) throws SQLException {
		userMapper.setUserImg(user);
	}

	@Override
	public void sendEmail(EmailData emailData) throws UnsupportedEncodingException, MessagingException {
		MimeMessage message = mailSender.createMimeMessage();
		log.debug("to : {}", Message.RecipientType.TO);
		log.debug("admin : {}", ADMIN_ADDRESS);
        message.addRecipients(Message.RecipientType.TO, ADMIN_ADDRESS);
        message.setSubject("[WhereIsMyHome 관리자 요청 사항] " + emailData.getSubject());
        String text = "";
        text+= "<div style='margin:100px;'>";
        text+= "<div align='center' style='border:1px solid black; font-family:verdana';>";
        text+= "<h3 style='color:blue;'>[WhereIsMyHome] 요청사항입니다.</h3>";
        text+= "<div style='font-size:130%'>";
        text+= emailData.getMessage() + "<strong>";
        text+= "</strong><div><br/> ";
        text+= "</div>";
        message.setText(text, "utf-8", "html");
        message.setFrom(new InternetAddress(ADMIN_ADDRESS, emailData.getEmail()));
        
        mailSender.send(message);	
	}

	@Override
	public void sendPassEmail(EmailData emailData) throws UnsupportedEncodingException, MessagingException {
		SimpleMailMessage message = new SimpleMailMessage();
        message.setTo(emailData.getEmail());
        message.setFrom(ADMIN_ADDRESS);
        message.setSubject(emailData.getSubject());
        message.setText(emailData.getMessage());

        mailSender.send(message);	
	}

	@Override
	public String makeRandomPass(int len) {

		// ASCII 범위 – 영숫자(0-9, a-z, A-Z)
        final String chars = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789";
 
        SecureRandom random = new SecureRandom();
        StringBuilder sb = new StringBuilder();
 
        // 루프의 각 반복은 주어진 문자에서 무작위로 문자를 선택합니다.
        // ASCII 범위를 `StringBuilder` 인스턴스에 추가합니다.
        for (int i = 0; i < len; i++) {
            int randomIndex = random.nextInt(chars.length());
            sb.append(chars.charAt(randomIndex));
        }
 
        return sb.toString();
	}

	@Override
	public int idCheck(User user) throws SQLException {
		return userMapper.idCheck(user);
	}

}
