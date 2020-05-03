package admin.book.action;

import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import action.Action;
import admin.book.svc.DeleteProService;
import vo.ActionForward;

public class DeleteProAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		ActionForward forward = null;
		// ============ 파라미터로 책 아이디, 입력받은 비밀번호 가져오기
		String[] bookIDList = request.getParameterValues("bookIDList");
		String pw = request.getParameter("pw");

		// session 에서 관리자 아이디 가져오기
		HttpSession session = request.getSession();
		String uID = (String)session.getAttribute("uID");
		
		// 관리자 비밀번호 일치 여부
		DeleteProService deleteProService = new DeleteProService();
		boolean isRightUser = deleteProService.isRightUser(uID, pw);
		
		if(!isRightUser) { // 관리자 비밀번호 불일치 시 
		    response.setContentType("text/html; charset=UTF-8");
		    PrintWriter out = response.getWriter();
		    out.println("<script>");
		    out.println("alert('비밀번호가 일치하지 않습니다!')");
		    out.println("history.back()");
		    out.println("</script>");
		} else {
		    // ============= 책 삭제하기
			System.out.println("책 삭제하기 전");
		    boolean isremoveArticleSuccess = deleteProService.removeArticle(bookIDList);
		    System.out.println(isremoveArticleSuccess);
		    
		    if (!isremoveArticleSuccess) { // 책 삭제 실패된 경우
		        response.setContentType("text/html; charset=UTF-8");
                PrintWriter out = response.getWriter();
                out.println("<script>");
                out.println("alert('글 삭제 실패!')");
                out.println("history.back()");
                out.println("</script>");
            } else {
                forward = new ActionForward();
                forward.setPath("List.abook");
                forward.setRedirect(true);
            }
		}
		
		return forward;
	}

}
