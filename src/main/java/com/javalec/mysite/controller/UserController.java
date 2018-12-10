package com.javalec.mysite.controller;
/*
 * 1. ������ ���� ��Ʈ���� ��� ������ ��Ʈ������, �׸��� �����ʹ� �𵨿� ��� ����.

 * 2. �˻� ������ ����  VO Ŀ�ǵ忡�� ���� �Ķ���͸� �޾� ���̰� �ϱ� ���� @RequestParam ���� ����
 * 
 * 3. @ModelAttribute �� ���� �� ������ ����.
 * 
 * 4. @SessionAttribute�� ����  ���ǿ� ������ ����..
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
//user �� �̸����� ���ǿ� ����.
//@SessionAttributes("user")
public class UserController {

	@Autowired
	private UserService userService ;
	
	
	
	
	//1. method ���� 
	//2. Setlvet API�� �̿��� ���ǿ�  ����� �̸��� �Է���..
	@RequestMapping(value="/login.do", method=RequestMethod.POST )
	public String login(UserVO vo, HttpSession session) {
		// TODO Auto-generated method stub
		System.out.println("login service controller");

		String result= userService.login(vo, session) ;
		return result ;
	}

	// 1. login.do �� get��û �� �α��� ȭ�鿡 �ʱⰪ ���� ..
	// 2. ModelAttribute ������ ���� command ��ü �̸��� ����..
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
		System.out.println("�α׾ƿ� ó��");
		
		// 1. �������� ����� ���� ��ü�� ���� �����Ѵ�.
		session.invalidate();
		
		// 2. ���� ������, ���� ȭ������ �̵��Ѵ�.
		return "main/login";
	}


	
}
