package com.javalec.mysite.controller;
/*
 * 1. °ü¸®ÀÇ Æí¸®»ó ÄÁÆ®·ÑÀÇ ¸ðµç ¸®ÅÏÀ» ½ºÆ®¸µÀ¸·Î, ±×¸®°í µ¥ÀÌÅÍ´Â ¸ðµ¨¿¡ ´ã¾Æ Àü´Þ.

 * 2. °Ë»ö Á¶°ÇÀ» À§ÇØ  VO Ä¿¸Çµå¿¡´Â ¾ø´Â ÆÄ¶ó¹ÌÅÍ¸¦ ¹Þ¾Æ µéÀÌ°Ô ÇÏ±â À§ÇÑ @RequestParam ¼³Á¤ º¯°æ
 * 
 * 3. @ModelAttribute ¸¦ ÅëÇÑ À¥ ÆäÀÌÁö ¼³Á¤.
 * 
 * 4. @SessionAttribute¸¦ ÅëÇÑ  ¼¼¼Ç¿¡ µ¥ÀÌÅÍ ÀúÀå..
 * 
 */

import java.io.File;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.multipart.MultipartFile;

import com.javalec.mysite.dao.UserDAO;
import com.javalec.mysite.service.BoardService;
import com.javalec.mysite.vo.BoardVO;
import com.javalec.mysite.vo.UserVO;

@Controller("board")
@SessionAttributes("board")
@RequestMapping("/board")
public class BoardController {

	@Autowired
	private BoardService boardService ;
	
	// ÄÁÆ®·Î·Ñ·¯ÀÇ ¿äÃ» ½Ã ¼öÇàµÉ ¸Þ¼Òµå ¼±¾ð!!
	@RequestMapping( value="/write.do", method=RequestMethod.GET )
	public String insertBoard( ) throws Exception {
		return "board/insertBoard";
	}

	
	
	@RequestMapping( value="/write.do", method=RequestMethod.POST )	
	public String insertBoard(BoardVO vo ) throws Exception {
		MultipartFile uploadFile = vo.getUploadFile() ;
		if(!uploadFile.isEmpty()) {
			String fileName = uploadFile.getOriginalFilename() ;
			uploadFile.transferTo(new File("D:/" +fileName));
			
		}
		
		System.out.println("게시판 입력 처리 수행!!");
		boardService.insertBoard(vo);
		return "redirect:getBoardList.do" ;
	}
	
	@RequestMapping("/deleteBoard.do")
	public String deleteBoard(BoardVO vo) {
		System.out.println("게시판 삭제 처리");
		
		// 1. »ç¿ëÀÚ ÀÔ·Â Á¤º¸ ÃßÃâ
		
		// 2. DB ¿¬µ¿ Ã³¸®
		boardService.deleteBoard(vo);
		
		// 3. È­¸é ³×ºñ°ÔÀÌ¼Ç
		//return "getBoardList.do";
		return "redirect:getBoardList.do" ; 
	}
	
	@RequestMapping("/getBoard.do")
	public String getBoard(BoardVO vo,  Model model) {
		System.out.println("±Û »ó¼¼ Á¶È¸ Ã³¸®");
		
		// 1. °Ë»öÇÒ °Ô½Ã±Û ¹øÈ£ ÃßÃâ
		// ¸®Äù½ºÆ®¿¡¼­ seq¸¦ Ã£ÀÌ ¾ÊÀ¸¸é ¾î¶»°Ô µÇ´Â°¡ ?
		
		// 2. DB ¿¬µ¿ Ã³¸®
		BoardVO board = boardService.getBoard(vo);
		
		// 3. °Ë»ö °á°ú¸¦ ¼¼¼Ç¿¡ ÀúÀåÇÏ°í »ó¼¼ È­¸éÀ» ¸®ÅÏ
		/*
		HttpSession session = request.getSession();
		session.setAttribute("board", board);
		return "getBoard";*/
		// ¸ðµ¨ Á¤º¸ ÀúÀå.
		model.addAttribute("board", board) ;

		return "board/getBoard" ;
	}
	
	@ModelAttribute("conditionMap")
	public Map<String, String> searchConditionMap(){
		System.out.println("conditionMap을 생성한다!!");
		Map<String, String> conditionMap = new HashMap<String, String>() ;
		conditionMap.put("제목", "TITLE");
		conditionMap.put("내용", "CONTENT") ;
		
		return conditionMap ;
	}
	

	@RequestMapping("/getBoardList.do")
/*	public String getBoardList(BoardVO vo, BoardDAO boardDAO, Model model) {
		System.out.println("±Û ¸ñ·Ï °Ë»ö Ã³¸®");
		model.addAttribute("boardList", boardDAO.getBoardList()) ;
		return "getBoardList.jsp";
	}
	*/
	public String getBoardList(@RequestParam(value="searchCondition", defaultValue="TITLE", required=false) String condition
			,@RequestParam( value="p", required=true, defaultValue="1") Integer page
			, @RequestParam(value="searchKeyword", defaultValue="", required=false) String keyword
			, Model model, BoardVO vo) {
	
		
		System.out.println("리스트 조회 수행!!");
		System.out.println("condition : " + condition) ;
		System.out.println("keyword : " + keyword) ;
	
		vo.setSearchCondition(condition);
		vo.setSearchKeyword(keyword);
		
		Map<String, Object> map = boardService.getBoardList(vo, page) ;
		
		model.addAttribute("map", map) ;
	return "board/getBoardList";
}
	
	

	@RequestMapping("/updateBoard.do")
	public String updateBoard(@ModelAttribute("board") BoardVO vo) {
		System.out.println("Update board ..!!");

		System.out.println("¹øÈ£ :" + vo.getSeq());
		System.out.println("Á¦¸ñ :" + vo.getTitle());
		System.out.println("ÀÛ¼ºÀÚ :" + vo.getWriter());
	
		boardService.updateBoard(vo);
		return "redirect:getBoardList.do";
	}
	
}
