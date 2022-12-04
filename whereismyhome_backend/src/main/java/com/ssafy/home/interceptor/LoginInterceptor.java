package com.ssafy.home.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.Jwts;
import lombok.extern.slf4j.Slf4j;

@Component
@Slf4j
public class LoginInterceptor implements HandlerInterceptor {
	final static String SECRET_KEY="ssafy";
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object Handler)
			throws Exception {
		log.info("interceptor 작동");
		log.debug("요청 메소드 종류:{}",request.getMethod());
		if(HttpMethod.OPTIONS.matches(request.getMethod())) {
			log.debug("사용자 지정 헤더 사용");
			return true;
		}
		
		log.debug(request.getHeader("access-token"));
		final String token = request.getHeader("access-token");
		
		//토큰 존재여부 체크
		if(token==null) {
			log.debug("헤더에 access-token 정보 없음");
			response.getWriter().append("not login");
			return false;
		}
		//토큰 유효성 체크
		try {
			Jwts.parser()
					.setSigningKey(SECRET_KEY.getBytes("UTF-8"))
					.parseClaimsJws(token);		
			log.debug("유효한 토큰");
			return true;
		} catch (Exception e) {
			log.debug("토큰이 유효하지 않음");
			response.getWriter().append("disavailable token");
			return false;
		}
	}
}
