package com.javalec.mysite.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.javalec.mysite.dao.GuestbookDao;


import com.javalec.mysite.vo.GuestbookVo;


@Service("guestbookService")
public class GuestBookService implements IGuestBookService {

	@Autowired
	//private IGuestbookDao guestBookDao ;
	//private GuestbookJDBCDao guestBookDao ;
	private GuestbookDao guestBookDao ;
	
	
	
	
	@Override
	public void insertGuestBook(GuestbookVo vo) {
		// TODO Auto-generated method stub
		guestBookDao.insert(vo);
	}

	@Override
	public void deleteGuestBook(GuestbookVo vo) {
		// TODO Auto-generated method stub
		guestBookDao.delete(vo);
	}

	@Override
	public List<GuestbookVo> getGuestBookList() {
		// TODO Auto-generated method stub
		return guestBookDao.getList();
	}

	@Override
	public List<GuestbookVo> getGuestBookKeywordList(String keyword) {
		// TODO Auto-generated method stub
		return guestBookDao.getKeywordList(keyword);
	}

}
