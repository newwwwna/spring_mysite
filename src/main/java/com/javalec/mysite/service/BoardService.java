package com.javalec.mysite.service;

import java.util.List;
import java.util.Map;

import com.javalec.mysite.vo.BoardVO;

public interface BoardService {

	
	public void insertBoard(BoardVO vo) ;
	public void updateBoard(BoardVO vo) ;
	public void deleteBoard(BoardVO vo) ;
	public BoardVO getBoard(BoardVO vo) ;
	public List<BoardVO> getBoardList() ;
	public Map<String, Object>  getBoardList(BoardVO vo, int currentPage) ;
	
}
