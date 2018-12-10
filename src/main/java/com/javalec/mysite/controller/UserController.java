package com.javalec.mysite.controller;
/*
 * 1. 관리의 편리상 컨트롤의 모든 리턴을 스트링으로, 그리고 데이터는 모델에 담아 전달.

 * 2. 검색 조건을 위해  VO 커맨드에는 없는 파라미터를 받아 들이게 하기 위한 @RequestParam 설정 변경
 * 
 * 3. @ModelAttribute 를 통한 웹 페이지 설정.
 * 
 * 4. @SessionAttribute를 통한  세션에 데이터 저장..
 * 
 */

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.SessionAttributes;

import com.javalec.mysite.service.UserService;
import com.javalec.mysite.vo.UserVO;

@Controller
@RequestMapping("/user")
//user 란 이름으로 세션에 저장.
//@SessionAttributes("user")
public class UserController {

	@Autowired
	private UserService userService ;
	
	
	
	
	//1. method 설정 
	//2. Setlvet API를 이용해 세션에  사용자 이름을 입력함..
	@RequestMapping(value="/login.do", method=RequestMethod.POST )
	public String login(UserVO vo, HttpSession session) {
		// TODO Auto-generated method stub
		System.out.println("login service controller");

		String result= userService.login(vo, session) ;
		return result ;
	}

	// 1. login.do 로 get요청 시 로그인 화면에 초기값 세팅 ..
	// 2. ModelAttribute 설정에 의해 command 객체 이름을 변경..
	@RequestMapping(value="/login.do", method=RequestMethod.GET )
	public String loginView(@ModelAttribute("user") UserVO vo) {
		// TODO Auto-generated method stub
		System.out.println("Login View request controller");
		vo.setId("1");
		vo.setPassword("aa");
		return "main/login"; 
	}
	

	@RequestMapping("/logout.do") 
	public String logout(HttpSession session) {
		System.out.println("로그아웃 처리");
		
		// 1. 브라우저와 연결된 세션 객체를 강제 종료한다.
		session.invalidate();
		
		// 2. 세션 종료후, 메인 화면으로 이동한다.
		return "main/login";
	}


	
}
