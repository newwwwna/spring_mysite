package com.javalec.mysite.dao;
/*
 * 
 * JDBC Template 객체를 통한 DAO 객체 구현...
 */
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.javalec.mysite.vo.BoardVO;

//@Repository
public class BoardDAOSpring{
	
	private Connection conn = null ;
	private PreparedStatement pstmt = null ;
	private ResultSet rs = null ;
	
	
	private String boardInsert ="insert into board(seq, title, writer, content) values("
			+ "(SELECT nvl(max(seq),0) + 1 from board), ?,?,?)";
	private String boardUpdate ="update board set title=?, content=? where seq=?";
	private String boardDelete="delet board where seq=?";
	private String boardGet ="SELECT * FROM BOARD WHERE SEQ=?";
	private String board_List = "SELECT * FROM BOARD ORDER BY SEQ DESC"; 
		
	// XML 설정에 의해 JdbcTemplate을 주입해 준다..
	@Autowired
	private JdbcTemplate jdbcTemplate ;
	
	public void insertBoard(BoardVO vo) {
		System.out.println("Board 입력 수행");
			jdbcTemplate.update(boardInsert, vo.getTitle(), vo.getWriter(), vo.getContent());
	}

	
	
	public void updateBoard(BoardVO vo) {
		System.out.println("Board 수정  수행");
		jdbcTemplate.update(boardUpdate, vo.getTitle(), vo.getContent(), vo.getSeq());
	}

		
	public void deleteBoard(BoardVO vo) {
		System.out.println("Board 삭제 수행");
		
		jdbcTemplate.update(boardDelete, vo.getSeq());
		
	}

//글 상세 조회
	public BoardVO getBoard(BoardVO vo) {
		System.out.println("Board 상세조회 수행");
		Object [] args = {vo.getSeq()} ;
		return jdbcTemplate.queryForObject(boardGet,args, new BoardRowMapper()) ;
	}

//글 목록 조회
	public List<BoardVO> getBoardList() {
		System.out.println("SPRING Board 상세조회 수행");
		
		return jdbcTemplate.query(board_List, new BoardRowMapper()) ;
	}
}
