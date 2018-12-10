package com.javalec.mysite.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.javalec.mysite.dao.GuestbookDao;
import com.javalec.mysite.service.IGuestBookService;
import com.javalec.mysite.vo.GuestbookVo;

@Controller("guestbook")
@RequestMapping("/guestbook")
public class GuestBookController {

	@Autowired
	private IGuestBookService guestbookService ;
	
	
	
	@RequestMapping(value="/insert.do")
	public String insertGuestBook(GuestbookVo vo) {
		guestbookService.insertGuestBook(vo);

		return "redirect:getguestbooklist.do" ;
	}
	
	@RequestMapping("/deleteform.do")
	public String deleteForm() {
		System.out.println("�궘�젣�뤌 �씠�룞 �닔�뻾");
		return "guestbook/deleteform" ; 
	}
	
	@RequestMapping("/delete.do")
	public String deleteGuestBook(GuestbookVo vo) {
		System.out.println("�궘�젣 �옉�뾽 �닔�뻾");
		guestbookService.deleteGuestBook(vo);
		return "redirect:getguestbooklist.do" ;
	}

	                  
	@RequestMapping("/getguestbooklist.do")
	public String getGuestBookList(GuestbookDao dao, Model model ) {
	System.out.println("게스트북 리스트 조회 호출!!");
	List<GuestbookVo> list =   guestbookService.getGuestBookList() ;
	model.addAttribute("list", list) ;
 
	return "guestbook/guestbookList";
}
	
	@RequestMapping("/searchkeywordlist.do")
	public String getGuestBookList(GuestbookDao dao, Model model, @RequestParam(value="searchKeyword", defaultValue="", required=false) String keyword) {
		System.out.println("寃��깋 �궎�뱶 :" + keyword);

	List<GuestbookVo> list =   guestbookService.getGuestBookKeywordList(keyword) ;
	
	model.addAttribute("list", list) ;
	model.addAttribute("searchKeyword", keyword) ;
	return "guestbook/guestbookList";
	}	

}
