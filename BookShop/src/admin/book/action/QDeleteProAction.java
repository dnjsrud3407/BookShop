package admin.book.action;

import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import action.Action;
import admin.book.svc.DeleteProService;
import admin.book.svc.QnReDeleteProService;
import vo.ActionForward;

public class QDeleteProAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		ActionForward forward = null;
		
		// ============ 파라미터로 글 참조번호리스트(boardRe_refList), 입력받은 비밀번호 가져오기
		String[] boardRe_refList = request.getParameterValues("boardRe_refList");
		String pw = request.getParameter("pw");
		
		// session 에서 관리자 아이디 가져오기
		HttpSession session = request.getSession();
		String uID = (String)session.getAttribute("uID");
		
		// 관리자 비밀번호 일치 여부
		DeleteProService deleteProService = new DeleteProService();
		boolean isRightUser = deleteProService.isRightUser(uID, pw);
		
		QnReDeleteProService qDeleteProService = new QnReDeleteProService();

		if(!isRightUser) { // 관리자 비밀번호 불일치 시 
		    response.setContentType("text/html; charset=UTF-8");
		    PrintWriter out = response.getWriter();
		    out.println("<script>");
		    out.println("alert('비밀번호가 일치하지 않습니다!')");
		    out.println("history.back()");
		    out.println("</script>");
		} else {
			// 답변 글 삭제하기
			int kID = 102;
			boolean isRemoveBoard = qDeleteProService.deleteBoard(boardRe_refList, kID);
			
			if(!isRemoveBoard) {	// 답변 삭제 실패된 경우
				response.setContentType("text/html; charset=UTF-8");
                PrintWriter out = response.getWriter();
                out.println("<script>");
                out.println("alert('등록된 답변이 없습니다')");
                out.println("history.go(-2)");
                out.println("</script>");
			} else {
				forward = new ActionForward();
				forward.setPath("QList.abook");
				forward.setRedirect(true);
			}
		}
		
		return forward;
	}

}
