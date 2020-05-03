package admin.book.action;

import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import action.Action;
import vo.ActionForward;

public class ReviewDeleteFormAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		ActionForward forward = null;
		
		// 파라미터로 답변글번호(boardRe_refList) 가져오기
		if(request.getParameterValues("boardRe_refList") != null) {
			String[] boardRe_refList = request.getParameterValues("boardRe_refList");
			
			request.setAttribute("boardRe_refList", boardRe_refList);
			
			forward = new ActionForward();
			forward.setPath("./admin/book/reviewDeleteForm.jsp");
		} else {	// boardRe_refList가 null이라면
			response.setContentType("text/html; charset=UTF-8");
            PrintWriter out = response.getWriter();
            
            out.println("<script>");        
            out.println("alert('삭제할 답변을 선택해주세요')");
            // 이전 페이지로 돌아가기
            out.println("history.back()");       
            out.println("</script>");
		} 
		
		return forward;
	}

}
