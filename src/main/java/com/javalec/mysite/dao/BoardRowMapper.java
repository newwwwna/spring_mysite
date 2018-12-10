package com.javalec.mysite.dao;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.javalec.mysite.vo.BoardVO;

public class BoardRowMapper implements RowMapper<BoardVO> {
	
	public BoardVO mapRow(ResultSet rs, int rowNum)throws SQLException{
	
		BoardVO board = new BoardVO() ;
		board.setSeq(rs.getInt("SEQ"));
		board.setTitle(rs.getString("TITLE"));
		board.setWriter(rs.getString("WRITER"));
		board.setContent(rs.getString("CONTENT"));
		board.setRegDate(rs.getString("REGDATE"));
		board.setCnt(rs.getInt("CNT"));
		
		return board; 
	}
	
}
