package com.javalec.mysite.dao;
/*
 * 
 * JDBC Template ��ü�� ���� DAO ��ü ����...
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
		
	// XML ������ ���� JdbcTemplate�� ������ �ش�..
	@Autowired
	private JdbcTemplate jdbcTemplate ;
	
	public void insertBoard(BoardVO vo) {
		System.out.println("Board �Է� ����");
			jdbcTemplate.update(boardInsert, vo.getTitle(), vo.getWriter(), vo.getContent());
	}

	
	
	public void updateBoard(BoardVO vo) {
		System.out.println("Board ����  ����");
		jdbcTemplate.update(boardUpdate, vo.getTitle(), vo.getContent(), vo.getSeq());
	}

		
	public void deleteBoard(BoardVO vo) {
		System.out.println("Board ���� ����");
		
		jdbcTemplate.update(boardDelete, vo.getSeq());
		
	}

//�� �� ��ȸ
	public BoardVO getBoard(BoardVO vo) {
		System.out.println("Board ����ȸ ����");
		Object [] args = {vo.getSeq()} ;
		return jdbcTemplate.queryForObject(boardGet,args, new BoardRowMapper()) ;
	}

//�� ��� ��ȸ
	public List<BoardVO> getBoardList() {
		System.out.println("SPRING Board ����ȸ ����");
		
		return jdbcTemplate.query(board_List, new BoardRowMapper()) ;
	}
}
