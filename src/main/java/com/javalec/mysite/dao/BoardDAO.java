package com.javalec.mysite.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.javalec.mysite.common.JDBCUtil;
import com.javalec.mysite.vo.BoardVO;

@Repository
public class BoardDAO {

	private Connection conn = null ;
	private PreparedStatement pstmt = null ;
	private ResultSet rs = null ;
	
	
	private String boardInsert ="insert into board(seq, title, writer, content) values("
			+ "(SELECT nvl(max(seq),0) + 1 from board), ?,?,?)";
	private String boardUpdate ="update board set title=?, content=? where seq=?";
	private String boardDelete="delete board where seq=?";
	private String boardGet ="SELECT SEQ, TITLE, WRITER, CONTENT, TO_CHAR(REGDATE, 'yyyy/mm/dd') REGDATE, CNT FROM BOARD WHERE SEQ=?";
	private String board_List   = "SELECT * FROM ( " 
			  +   "SELECT SEQ, TITLE, WRITER, CONTENT, REGDATE, CNT , ROWNUM AS RN FROM "
			  +   "(SELECT SEQ, TITLE, WRITER, CONTENT, TO_CHAR(REGDATE, 'yyyy/mm/dd') REGDATE, CNT  FROM BOARD "
			  +   "  ORDER BY SEQ DESC )) " 
			  +   " 	WHERE (?-1)*?+1 <= rn and rn <= ?*? ";
	
	private String board_List_t1 = "SELECT * FROM ( " 
							  +   "SELECT SEQ, TITLE, WRITER, CONTENT, REGDATE, CNT , ROWNUM AS RN FROM "
							  +   "(SELECT SEQ, TITLE, WRITER, CONTENT, TO_CHAR(REGDATE, 'yyyy/mm/dd') REGDATE, CNT  FROM BOARD "
							  +   " WHERE title like ? ORDER BY SEQ DESC )) " 
							  +   " 	WHERE (?-1)*?+1 <= rn and rn <= ?*? ";
	private String board_List_c = " SELECT * FROM ( "
	                          + " SELECT SEQ, TITLE, WRITER, CONTENT, REGDATE, CNT , ROWNUM AS RN FROM "
	                          + " (SELECT SEQ, TITLE, WRITER, CONTENT, TO_CHAR(REGDATE, 'yyyy/mm/dd') REGDATE, CNT  FROM BOARD "
	                          + " WHERE content like ? ORDER BY SEQ DESC )) "
	                          + " WHERE (?-1)*?+1 <= rn and rn <= ?*? " ;
		
	private String total_count   = "SELECT count(*) FROM BOARD";
	
	private String total_count_t = "SELECT count(*) FROM BOARD WHERE title like ? " ;
							 
	private String total_count_c = "SELECT count(*) FROM BOARD WHERE Content like ? ";
	
	
	@Autowired
	private DataSource ds ;
	
	
	public void insertBoard(BoardVO vo) {
		System.out.println("Board 입력 수행");
		
		try {
			conn = ds.getConnection() ;
			pstmt = conn.prepareStatement(boardInsert) ;
			pstmt.setString(1, vo.getTitle());
			pstmt.setString(2, vo.getWriter());
			pstmt.setString(3, vo.getContent());
			pstmt.executeUpdate() ;
			
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			JDBCUtil.close(pstmt, conn);
		}
		
	}

	
	
	public void updateBoard(BoardVO vo) {
		System.out.println("Board 수정  수행");
		
		try {
			conn = ds.getConnection() ;
			pstmt = conn.prepareStatement(boardUpdate) ;
			pstmt.setString(1, vo.getTitle());
			pstmt.setString(2, vo.getContent());
			pstmt.setInt(3, vo.getSeq());
			pstmt.executeUpdate() ;
			
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			JDBCUtil.close(pstmt, conn);
		}
		
	}

		
	public void deleteBoard(BoardVO vo) {
		System.out.println("Board 삭제 수행");
		
		try {
			conn = ds.getConnection() ;
			pstmt = conn.prepareStatement(boardDelete) ;
			pstmt.setInt(1, vo.getSeq());
			pstmt.executeUpdate() ;
			
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			JDBCUtil.close(pstmt, conn);
		}
		
	}

//글 상세 조회
	public BoardVO getBoard(BoardVO vo) {
		System.out.println("Board SELECT !!");
		BoardVO board = null ;
		try {
			conn = ds.getConnection() ;
			pstmt = conn.prepareStatement(boardGet) ;
			pstmt.setInt(1, vo.getSeq());
			rs = pstmt.executeQuery();
			if(rs.next()) {
				board = new BoardVO() ;	
				board.setSeq(rs.getInt("SEQ"));
				board.setTitle(rs.getString("TITLE"));
				board.setWriter(rs.getString("WRITER"));
				board.setContent(rs.getString("CONTENT"));
				board.setRegDate(rs.getString("REGDATE"));
				board.setCnt(rs.getInt("CNT"));
				
			}
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			JDBCUtil.close(rs, pstmt, conn);
		}
		return board;
	}

//글 목록 조회
	public List<BoardVO> getBoardList() {
		System.out.println("DAO Board 상세조회 수행11111");
		List<BoardVO> boardList = new ArrayList<BoardVO>() ;
		try {
			conn = ds.getConnection() ;
			pstmt = conn.prepareStatement(board_List) ;
			rs = pstmt.executeQuery();
			while(rs.next()) {
				BoardVO board = new BoardVO() ;
				
				board.setSeq(rs.getInt("SEQ"));
				board.setTitle(rs.getString("TITLE"));
				board.setWriter(rs.getString("WRITER"));
				board.setContent(rs.getString("CONTENT"));
				board.setRegDate(rs.getString("REGDATE"));
				board.setCnt(rs.getInt("CNT"));
				
				boardList.add(board) ;
			}
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			JDBCUtil.close(rs, pstmt, conn);
		}
		return boardList ;
	}
	
	public List<BoardVO> getBoardList(BoardVO vo, int page, int size) {
		System.out.println("DAO Board 상세조회 수행");
		List<BoardVO> boardList = new ArrayList<BoardVO>() ;
		try {
			conn = ds.getConnection() ;
			
			System.out.println("검색조건 :" + vo.getSearchCondition());
			
			if(vo.getSearchCondition().equals("")) {
				System.out.println(" **** here 1");
				pstmt = conn.prepareStatement(board_List) ;
				pstmt.setInt( 1, page );
				pstmt.setInt( 2, size );
				pstmt.setInt( 3, page );
				pstmt.setInt( 4, size );
			}
			else if(vo.getSearchCondition().equals("TITLE")) {
				System.out.println("  **** here 2");
				pstmt = conn.prepareStatement(board_List_t1) ;
				pstmt.setString(1, "%" +vo.getSearchKeyword() +"%");
				pstmt.setInt( 2, page );
				pstmt.setInt( 3, size );
				pstmt.setInt( 4, page );
				pstmt.setInt( 5, size );
			}else if(vo.getSearchCondition().equals("CONTENT")) {
				System.out.println("here3" );
				pstmt = conn.prepareStatement(board_List_c) ;
				pstmt.setString(1, "%"+vo.getSearchKeyword()+"%");
				pstmt.setInt( 2, page );
				pstmt.setInt( 3, size );
				pstmt.setInt( 4, page );
				pstmt.setInt( 5, size );			
			}


			rs = pstmt.executeQuery();
			while(rs.next()) {
				BoardVO board = new BoardVO() ;
				
				board.setSeq(rs.getInt("SEQ"));
				board.setTitle(rs.getString("TITLE"));
				board.setWriter(rs.getString("WRITER"));
				board.setContent(rs.getString("CONTENT"));
				board.setRegDate(rs.getString("REGDATE"));
				board.setCnt(rs.getInt("CNT"));
				
				boardList.add(board) ;
			}
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			JDBCUtil.close(rs, pstmt, conn);
		}
		return boardList ;
	}
	
	public int getTotalCount( BoardVO vo ) {
		int totalCount = 0;
		
		try {
			
			String searchCondition = vo.getSearchCondition() ;
			String keyword = vo.getSearchKeyword().trim() ; 
			System.out.println("serarch : " +searchCondition);
			System.out.println("keyword : " + keyword);
			
			conn = ds.getConnection();
			if( "".equals( searchCondition ) ) {
				pstmt = conn.prepareStatement(total_count);
			}else if(searchCondition.equals("TITLE")) {
				pstmt = conn.prepareStatement(total_count_t) ;
				pstmt.setString(1, "%" + keyword + "%");

			}else if(searchCondition.equals("CONTENT")) {
				pstmt = conn.prepareStatement(total_count_c) ;
				pstmt.setString(1, "%" + keyword + "%");

			}

			rs = pstmt.executeQuery();
			if( rs.next() ) {
				totalCount = rs.getInt( 1 );
			}
			
		} catch (SQLException e) {
			e.printStackTrace() ;
		} finally {
			JDBCUtil.close(rs, pstmt, conn);
				 
		}
		
		return totalCount;
	}
	
}
