package com.puc.clinicas.security.handller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import com.puc.clinicas.security.transfer.JwtUserDto;
import com.puc.clinicas.security.util.JwtTokenGenerator;

@Component
public class CustomAuthenticationSuccessHandler implements AuthenticationSuccessHandler {

	@Value("mysecretkey")
	private String jwtSecret;

	@Override
	public void onAuthenticationSuccess(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse,
			Authentication authentication) throws IOException, ServletException {

		JwtUserDto jwtUserDto = JwtUserDto.buildFromAuthentication(authentication);

		httpServletResponse.setStatus(HttpServletResponse.SC_CREATED);
		httpServletResponse.getWriter().append(JwtTokenGenerator.generateToken(jwtUserDto, jwtSecret)).flush();
	}
}
