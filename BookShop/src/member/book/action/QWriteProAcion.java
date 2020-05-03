package member.book.action;

import java.io.PrintWriter;
import java.sql.Timestamp;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import action.Action;

import member.book.svc.QWriteProService;
import vo.ActionForward;
import vo.BoardBean;

public class QWriteProAcion implements Action {
	
	 
	
	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		System.out.println("QWriteProAcion");
		ActionForward forward = null;
		BoardBean bb = null;
		
		final String k1 = "상품문의";
		

		int bookID = Integer.parseInt(request.getParameter("bookID"));
		String boardWriter = request.getParameter("uID");
		String boardTitle = request.getParameter("boardTitle");
		String boardContent = request.getParameter("boardContent");
		
		bb = new BoardBean();
		bb.setBookID(bookID);
		bb.setBoardWriter(boardWriter);
		bb.setBoardTitle(boardTitle);
		bb.setBoardContent(boardContent);
		bb.setK1(k1);
//		bb.setK2(k2);
		bb.setBoardRegTime(new Timestamp(System.currentTimeMillis()));
		
		QWriteProService qWriteProService = new QWriteProService();
		boolean isWriteSuccess = qWriteProService.registQuestions(bb);
		
		if(!isWriteSuccess) { // 글쓰기 실패
			// HTML 문서 형식으로 출력하기 위해 response 객체의 setContentType() 메서드 호출 => HTML 타입 지정
			response.setContentType("text/html; charset=UTF-8");
			PrintWriter out = response.getWriter(); // HTML 태그 출력을 위한 Writer 객체 가져오기
			// out 객체의 println() 메서드를 호출하여 HTML 태그 작성
			out.println("<script>"); // 자바스크립트 실행을 위한 <script> 시작 태그
			out.println("alert('게시물 등록 실패!')"); // alert dialog 출력
			out.println("history.back()"); // 또는 out.println("history.go(-1)");  // 이전 페이지로 돌아가기
			out.println("</script>"); // 자바스크립트 종료 위한 <script> 끝 태그
		} else { // 글쓰기 성공
			forward = new ActionForward(); 
			forward.setPath("Book.book?bookID=" + bookID); 
			forward.setRedirect(true); 
		}
		
		return forward;
	}

}
