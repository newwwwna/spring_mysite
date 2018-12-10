package com.javalec.mysite.service;

import javax.servlet.http.HttpSession;

import org.springframework.web.bind.annotation.ModelAttribute;

import com.javalec.mysite.vo.UserVO;

public interface UserService {

	public String login(UserVO vo, HttpSession session) ;

	
}
