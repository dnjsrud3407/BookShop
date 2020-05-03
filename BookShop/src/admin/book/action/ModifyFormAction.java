package admin.book.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.simple.JSONArray;

import action.Action;
import admin.book.svc.DetailService;
import vo.ActionForward;
import vo.BookBean;

public class ModifyFormAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		ActionForward forward = null;
		
		// bookID, page 파라미터로 가져오기//
		int bookID = Integer.parseInt(request.getParameter("bookID"));
		String page = request.getParameter("page");
        
        // 제품 상세보기
        DetailService detailService = new DetailService();
        BookBean book = detailService.getArticle(bookID);
		
        // 가져온 book, page request 객체에 넣기
        request.setAttribute("book", book);
        request.setAttribute("page", page);
		
		forward = new ActionForward();
		forward.setPath("./admin/book/modifyForm.jsp");
		
		return forward;
	}

}
