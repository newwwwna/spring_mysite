package com.javalec.mysite.controller;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/main")
public class MainController {
	private static final Log log = 
		LogFactory.getLog( MainController.class  );



	
	
	@RequestMapping( "/main.do" )
	public String index() {
		return "main/main";
	}
	
	@ResponseBody
	@RequestMapping( "/hello" )
	public String hello(){
		log.debug( "MainController.hello() called...." );
		return "hello 페이지 로 가네요!!";
	}
}