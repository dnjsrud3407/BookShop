package admin.board.action;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import action.Action;
import admin.board.svc.BoardService;
import vo.ActionForward;
import vo.BoardBean;
import static access.Access.*;
public class FAQModifyProAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		ActionForward forward = null;
		System.out.println("FAQModifyProAction");
		// 리퀘스트 한글처리
		request.setCharacterEncoding("UTF-8");
		// 관리자 체크
		HttpSession session = request.getSession();
		if(!isAdmin(session)) {
			forward = deniedAccess(session);
			return forward;
		}
		// 세션 챙기기 + 액션에서 세션값을 활용하여 관리자인지를 체크한 후 관리자가 아니면 메인 홈페이지로 보냄
		
		BoardBean bb = null;
		// DB작업을 위해 서비스 객체 생성
		BoardService boardService = new BoardService();
		// 카테고리 관련
		String k1 = "FAQ";
		String k2 = request.getParameter("k2");
		// 글 번호 들고오기
		int boardNum = Integer.parseInt(request.getParameter("boardNum"));
		// 제목과 내용, 작성자
		String boardWriter = request.getParameter("boardWriter");
		String boardTitle = request.getParameter("boardTitle");
		String boardContent = request.getParameter("boardContent");
		
		// 작성일, 그룹번호, 글 레벨(답글 확인), 글 순서(답글 순서), 조회수, 상품 ID(상품 문의, 후기용)
		// BoardBean 에 파라미터 저장 및 생성
		bb = new BoardBean();
		bb.setBoardNum(boardNum); bb.setK1(k1); bb.setK2(k2); bb.setBoardWriter(boardWriter);
		bb.setBoardTitle(boardTitle); bb.setBoardContent(boardContent);
		
		// BoardBean 객체를 전달하여 서비스의 writeArticle() 메서드를 실행하여  DB에 글을 삽입하고, 성공 시 1을 반환받는다, 실패시 0을 반환
		List<String> deleteFileName = null; 
		int updateCount = boardService.modifyArticle(bb, deleteFileName);
		
		forward = new ActionForward();
		// 실패 시
		if(updateCount != 0) {
			// 성공 시
			// FAQ 는 작성한 것을 바로 리스트에서 볼 수 있도록 리스트로 이동한다.
			// FAQ 작성한거 상세보기
			forward.setPath("./FAQ.adb");
			forward.setRedirect(true);
		} else { 
			session.setAttribute("ErrorMSG", "게시글 작성에 실패하였습니다.");
			forward.setPath("failed.adb");
			forward.setRedirect(true);
			
		}
		
		return forward;
	}

}
