package board.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import action.Action;
import vo.ActionForward;
import java.net.URLDecoder;
import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import action.Action;
import admin.board.svc.BoardService;
import vo.ActionForward;
import vo.BoardBean;
import vo.PageInfo;

public class FAQListAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		ActionForward forward = null;
		System.out.println("FAQ 보기");
		request.setCharacterEncoding("UTF-8");
		
		// PageInfo 객체 선언
		PageInfo pageInfo = new PageInfo();
		int page = 1;  // 현 페이지 정보
		int limit = 10; // 한 페이지 당 출력할 게시물 수  - 10개
		if(request.getParameter("page") != null) {
			page = Integer.parseInt(request.getParameter("page"));
		}
		pageInfo.setPage(page);	// 들고온 페이지 정보가 없을 때 기본 페이지 1
		
		String k1 = "FAQ";
		String k2 = request.getParameter("k2");
		
		
		// BoardListService 인스턴스 생성 후 게시물 목록 갯수 가져오기
		BoardService boardService = new BoardService();
		int listCount = boardService.getListCount(k1, k2);
		System.out.println("listCount : " + listCount);
		// pageInfo limit, k1, k2 담기
		pageInfo.setLimit(limit); pageInfo.setK1(k1); pageInfo.setK2(k2);
//		 BoardListService 객체의 getArticleList() 메서드를 호출 하여 게시물 목록 가져오기
		// => 파라미터로 현재 페이지(page) 와 게시물 수(limit) 를 전달
		// => ArrayList<BoardBean> 타입 객체 리턴
		ArrayList<BoardBean> articleList = boardService.getArticleList(pageInfo);
		
		if(articleList != null) {
			request.setAttribute("articleList", articleList);
		}
		// 가져온 게시물 목록 리퀘스트에 저장
		
		// 페이지 계산
		// 1. 총 페이지 수 계산
		int maxPage = (int)((double)listCount / limit + 0.95);
		if(maxPage == 0) {
			maxPage = 1;
		}
		// 2. 시작 페이지 번호 계산
		int startPage = ((int)((double)page / 10 + 0.9) - 1) * 10 + 1;
		// 3. 마지막 페이지 번호 계산
		int endPage = startPage + 10 - 1;
		// 마지막 페이지 번호가 총 페이지 수 보다 클 경우 총 페이지 수를 마지막 페이지 번호로 설정
		if(endPage > maxPage) {
			endPage = maxPage;
		}
		
		// pageInfo 객체에 페이지 정보 저장(총 페이지, 시작 페이지, 현재 페이지, 끝 페이지, 총 게시글 수 등)
		pageInfo.setMaxPage(maxPage);
		pageInfo.setStartPage(startPage); 
		pageInfo.setPage(page);
		pageInfo.setEndPage(endPage);
		pageInfo.setListCount(listCount);
		pageInfo.setPageBlock(10);
		
		request.setAttribute("pageInfo", pageInfo);
		
		ArrayList<String> k2List = boardService.getk2List(k1);
		
		// FAQ 카테고리 목록
		request.setAttribute("k2List", k2List);
		
		
		forward = new ActionForward();
		forward.setPath("/board/FAQList.jsp");
		
		return forward;
	}



}


