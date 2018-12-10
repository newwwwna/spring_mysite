package com.javalec.mysite.service;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.javalec.mysite.dao.BoardDAOSpring;
import com.javalec.mysite.dao.UserDAO;
import com.javalec.mysite.vo.UserVO;

@Service("userService")
public class UserServiceImple implements UserService {

	@Autowired
	private UserDAO userDAO ;
	
	@Override
	public String login(UserVO vo, HttpSession session) {
		// TODO Auto-generated method stub

		UserVO user = userDAO.getUser(vo) ;
		// 2. DB 연동 처리
		if(user != null ) {
			session.setAttribute("userName", user.getName());
			session.setAttribute("user", user);
			return "main/main" ;
		}else {
			return "main/login"; 
		}
	}



}
