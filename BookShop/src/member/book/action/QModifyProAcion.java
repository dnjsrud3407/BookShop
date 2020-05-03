package member.book.action;

import static db.JdbcUtil.*;

import java.io.PrintWriter;
import java.sql.Connection;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import action.Action;
import dao.BookDAO;
import member.book.svc.QModifyProService;
import member.book.svc.ReviewModifyProService;
import vo.ActionForward;
import vo.BoardBean;
import vo.BookBean;

public class QModifyProAcion implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		System.out.println("QModifyProAcion");
		
		ActionForward forward = null;
		
		// 상품문의 수정
		int boardNum =Integer.parseInt(request.getParameter("boardNum"));
		String boardTitle = request.getParameter("boardTitle");
		String boardContent = request.getParameter("boardContent");
		int bookID = Integer.parseInt(request.getParameter("bookID"));
		String uID = request.getParameter("uID");
		
		System.out.println("글번호 : " + boardNum + "," + "글제목 :" + boardTitle + "," + "글내용 :" + boardContent);
		
		BoardBean boardBean = new BoardBean();
		boardBean.setBoardNum(boardNum);
		boardBean.setBoardTitle(boardTitle);
		boardBean.setBoardContent(boardContent);
		boardBean.setBoardWriter(uID);
		
		QModifyProService qModifyProService = new QModifyProService();
		boolean isModifySuccess = qModifyProService.modifyQuestion(boardBean);
		

			if(!isModifySuccess) {
				response.setContentType("text/html; charset=UTF-8");
				PrintWriter out = response.getWriter();
				out.println("<script>");
				out.println("alert('글 수정 실패!')"); // 경고 dialog출력
				out.println("history.go(-1)"); //back은 한칸만가능 go는 히스토리안에서 단계로 넘어갈수있음
				out.println("</script>");
				
			}else{
				forward = new ActionForward();
				forward.setPath("Book.book?bookID=" + bookID);
				forward.setRedirect(true);
			}
		
		
		return forward;
	}
}
