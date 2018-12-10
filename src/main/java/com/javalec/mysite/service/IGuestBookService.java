package com.javalec.mysite.service;

import java.util.List;

import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.javalec.mysite.dao.GuestbookDao;
import com.javalec.mysite.vo.GuestbookVo;

public interface IGuestBookService {

	public void insertGuestBook(GuestbookVo vo)  ;
	public void deleteGuestBook(GuestbookVo vo) ;
	public List<GuestbookVo> getGuestBookList()  ;
	public List<GuestbookVo> getGuestBookKeywordList(String keyword) ;
	
}
