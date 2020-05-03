package admin.board.action;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import action.Action;
import admin.board.svc.BoardService;
import board.svc.QListService;
import vo.ActionForward;
import vo.BoardBean;
import vo.PageInfo;
import static access.Access.*;
public class QNotAnswerListAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		ActionForward forward = null;
		System.out.println("1:1문의 보기");
		// 관리자 체크
		HttpSession session = request.getSession();
		if(!isAdmin(session)) {
			forward = deniedAccess(session);
			return forward;
		}
		// PageInfo 객체 선언
		PageInfo pageInfo = null; 
		
		int page = 1; // 현재 페이지 번호
		int limit = 10; // 한 페이지 당 출력할 게시물 수
		// page 파라미터가 존재할 경우 파라미터에 전달된 데이터를 현재 페이지 번호로 대체
		if(request.getParameter("page") != null) {
			page = Integer.parseInt(request.getParameter("page")); // 정수로 변환하여 저장
		}
		String k1 = "1:1문의";
		String k2 = request.getParameter("k2");
		
		// BoardListService 인스턴스 생성 후 게시물 목록 갯수 가져오기
		BoardService boardService = new BoardService();
		int listCount = boardService.getNAListCount(k1, k2);
		
		System.out.println("총 게시물 수 : " + listCount + "개");
		
		// pageInfo 에 기본적인 페이지 정보 담아가기
		pageInfo = new PageInfo(page, limit, k1, k2);
//		 BoardListService 객체의 getArticleList() 메서드를 호출 하여 게시물 목록 가져오기
		// => 파라미터로 현재 페이지(page) 와 게시물 수(limit) 를 전달
		// => ArrayList<BoardBean> 타입 객체 리턴
		ArrayList<BoardBean> articleList = boardService.getArticleList(pageInfo);
		// 가져온 게시물 목록 리퀘스트에 저장
		request.setAttribute("articleList", articleList);
		
		// 페이지 계산
		// 1. 총 페이지 수 계산
		int maxPage = (int)((double)listCount / limit + 0.95);
		// 2. 시작 페이지 번호 계산
		int startPage = ((int)((double)page / 10 + 0.9) - 1) * 10 + 1;
		// 3. 마지막 페이지 번호 계산
		int endPage = startPage + 10 - 1;
		// 마지막 페이지 번호가 총 페이지 수 보다 클 경우 총 페이지 수를 마지막 페이지 번호로 설정
		if(endPage > maxPage) {
			endPage = maxPage;
		}
		
		// pageInfo 객체에 페이지 정보 저장(총 페이지, 시작 페이지, 끝 페이지, 총 게시글 수)
		pageInfo.setMaxPage(maxPage);
		pageInfo.setStartPage(startPage); 
		pageInfo.setEndPage(endPage);
		pageInfo.setListCount(listCount);
		
		request.setAttribute("pageInfo", pageInfo);
		
		forward = new ActionForward();
		// 이벤트로 고칠것
//		forward.setPath("./board/FAQList.jsp");
		
		return forward;
	}

}
