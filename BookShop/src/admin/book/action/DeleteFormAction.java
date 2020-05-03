package admin.book.action;

import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import action.Action;
import vo.ActionForward;

public class DeleteFormAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		ActionForward forward = null;
		
		// list.jsp 에서 1~n개 삭제하는 경우 || detail.jsp 에서 1개 삭제하는 경우  모두 포함	
		if(request.getParameterValues("bookIDList") != null) {
			
			String[] bookIDList = request.getParameterValues("bookIDList");
			request.setAttribute("bookIDList", bookIDList);
			
			forward = new ActionForward();
			forward.setPath("./admin/book/deleteForm.jsp");
		} else {	// bookIDList가 null이라면
			response.setContentType("text/html; charset=UTF-8");
            PrintWriter out = response.getWriter();
            
            out.println("<script>");        
            out.println("alert('삭제할 상품을 선택해주세요')");
            // 이전 페이지로 돌아가기
            out.println("history.back()");       
            out.println("</script>");
		}
		
		return forward;
	}

}
