package com.javalec.mysite.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.javalec.mysite.dao.BoardDAO;
import com.javalec.mysite.vo.BoardVO;



@Service("boardService")
public class BoardServiceImple implements BoardService {

	private static final int LIST_SIZE = 5; //리스팅되는 게시물의 수
	private static final int PAGE_SIZE = 5; //페이지 리스트의 페이지 수	
	
	@Autowired
	//private BoardDAOSpring boardDAO ;
	private BoardDAO boardDAO ;
	
	@Override
	public void insertBoard(BoardVO vo) {
		// TODO Auto-generated method stub
		boardDAO.insertBoard(vo);

	}

	@Override
	public void updateBoard(BoardVO vo) {
		// TODO Auto-generated method stub
		boardDAO.updateBoard(vo);
	}

	@Override
	public void deleteBoard(BoardVO vo) {
		// TODO Auto-generated method stub
		boardDAO.deleteBoard(vo);
	}

	@Override
	public BoardVO getBoard(BoardVO vo) {
		// TODO Auto-generated method stub
		return boardDAO.getBoard(vo) ;
	}

	@Override
	public List<BoardVO> getBoardList() {
		// TODO Auto-generated method stub
		return boardDAO.getBoardList();
	}

		@Override
	public Map<String, Object> getBoardList(BoardVO vo, int currentPage) {
		// TODO Auto-generated method stub
			String keyword= vo.getSearchKeyword() ;
			
			int totalCount = boardDAO.getTotalCount( vo ); 
			
			
			int pageCount = (int)Math.ceil( (double)totalCount / LIST_SIZE );
			int blockCount = (int)Math.ceil( (double)pageCount / PAGE_SIZE );
			int currentBlock = (int)Math.ceil( (double)currentPage / PAGE_SIZE );
			
			//2. �뙆�씪誘명꽣 page 媛�  寃�利�
			if( currentPage < 1 ) {
				currentPage = 1;
				currentBlock = 1;
			} else if( currentPage > pageCount ) {
				currentPage = pageCount;
				currentBlock = (int)Math.ceil( (double)currentPage / PAGE_SIZE );
			}
			
			//3. view�뿉�꽌 �럹�씠吏� 由ъ뒪�듃瑜� �젋�뜑留� �븯湲곗쐞�븳 �뜲�씠�꽣 媛� 怨꾩궛
			int beginPage = currentBlock == 0 ? 1 : (currentBlock - 1)*PAGE_SIZE + 1;
			int prevPage = ( currentBlock > 1 ) ? ( currentBlock - 1 ) * PAGE_SIZE : 0;
			int nextPage = ( currentBlock < blockCount ) ? currentBlock * PAGE_SIZE + 1 : 0;
			int endPage = ( nextPage > 0 ) ? ( beginPage - 1 ) + LIST_SIZE : pageCount;
			
			
			
			List<BoardVO> list = boardDAO.getBoardList(vo, currentPage, LIST_SIZE ) ;
			
			Map<String, Object> map = new HashMap<String, Object>();
			
			
			System.out.println("list size : " + list.size());
			
			map.put( "list", list );
			map.put( "totalCount", totalCount );
			map.put( "listSize", LIST_SIZE );
			map.put( "currentPage", currentPage );
			map.put( "beginPage", beginPage );
			map.put( "endPage", endPage );
			map.put( "prevPage", prevPage );
			map.put( "nextPage", nextPage );
			map.put( "keyword", keyword );		
			
		return map ; 	
	}
	
	
}
