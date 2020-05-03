package member.account.action;

import java.io.PrintWriter;
import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import action.Action;
import member.account.svc.MiddleBookService;
import vo.ActionForward;
import vo.BookBean;

public class MiddleBookAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		// 메인에서 middleBook 타입 가져오기
		int type = 0; String BK2 = null;
		if(request.getParameter("type") != null || request.getParameter("type") != "") {
			type = Integer.parseInt(request.getParameter("type"));
		}
		
		MiddleBookService newBookService = new MiddleBookService();  
		ArrayList<BookBean> bookList = null;
		// type = 0 -> 베스트 셀러
		if(type == 0) {
			bookList = newBookService.getMiddleBookList();
		} else if(type == 1) {	// type = 1 -> 1단계 책
			BK2 = "1단계";
			bookList = newBookService.getMiddleBookList(BK2);
		} else if(type == 2) {	// type = 2 -> 2단계 책
			BK2 = "2단계";
			bookList = newBookService.getMiddleBookList(BK2);
		} else if(type == 3) {	// type = 3 -> 3단계 책
			BK2 = "3단계";
			bookList = newBookService.getMiddleBookList(BK2);
		}
		
		
		// 출력 함수 
    	response.setContentType("text/html; charset=UTF-8");
    	PrintWriter out = response.getWriter();
    	out.print("<div class='item active'>");
    	out.print("<ul class='thumbnails'>");
    	for(int i = 0; i < 4; i++) {
    		out.print("<li class='span3'>");
    		out.print("<div class='thumbnail' style='height:"+"280px"+"'>");
    		if (type == 0) {	// 신권일 때만 new Image 
    			out.print("<i class='tag'></i>");
			}
    		out.print("<a href='Book.book?bookID=" + bookList.get(i).getBookID() + "'><img src=\"upload/" + bookList.get(i).getBookImage() + "\" alt=\"\"></a>");
    		out.print("<div class='caption'>");
    		out.print("<h5>"+bookList.get(i).getBookTitle()+"</h5>");
    		out.print(bookList.get(i).getBookPublisher()+" | "+bookList.get(i).getBookPrice());
    		out.print("</div>");
    		out.print("</div>");
    		out.print("</li>");
    	}
    	out.print("</ul>");
    	out.print("</div>");
    	
    	out.print("<div class='item'>");
    	out.print("<ul class='thumbnails'>");
    	for(int i = 4; i < 8; i++) {
    		out.print("<li class='span3'>");
    		out.print("<div class='thumbnail' style='height:"+"280px"+"'>");
    		if (type == 0) {	// 신권일 때만 new Image 
    			out.print("<i class='tag'></i>");
			}
    		out.print("<a href='Book.book?bookID=" + bookList.get(i).getBookID() + "'><img src=\"upload/" + bookList.get(i).getBookImage() + "\" alt=\"\"></a>");
    		out.print("<div class='caption'>");
    		out.print("<h5>"+bookList.get(i).getBookTitle()+"</h5>");
    		out.print(bookList.get(i).getBookPublisher()+" | "+bookList.get(i).getBookPrice());
    		out.print("</div>");
    		out.print("</div>");
    		out.print("</li>");
    	}
    	out.print("</ul>");
    	out.print("</div>");
    	
		return null;
	}

}
