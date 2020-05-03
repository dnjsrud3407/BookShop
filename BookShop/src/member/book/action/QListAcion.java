package member.book.action;

import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import action.Action;
import member.account.svc.JoinCheckService;
import member.book.svc.QListService;
import vo.ActionForward;
import vo.BoardBean;
import vo.BookBean;

public class QListAcion implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		System.out.println("QListAcion - ActionForward");
		
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html; charset=UTF-8");
    	PrintWriter out = response.getWriter();
	
		boolean flag = false;
		String str ="";

		// 관리자 상품문의 답변 글 불러오는 액션
//		int num = Integer.parseInt(request.getParameter("num"));

//		QListService qListService = new QListService();
//		BookBean question = qListService.getQuestion(num);
		BoardBean answerBoard = null;
		
		int boardNum = Integer.parseInt(request.getParameter("boardNum"));
		int kID = 102;
		int bookID = Integer.parseInt(request.getParameter("bookID"));
		
		System.out.println(boardNum + ", " + kID + ", " + bookID);
		
		QListService qListService = new QListService();
		answerBoard = qListService.qnaAnswerBoard(boardNum, kID, bookID);
		
		String boardContent = "";
		String boardRegTime = "";
		
		if(answerBoard != null){
			boardContent = answerBoard.getBoardContent();
			boardRegTime = answerBoard.getBoardRegTime().toString();
			flag = true;
				
		}
		if(flag){ // 관리자 답변 뽑아옴
			out.print("<div> 판매자의 답변 :" +boardContent+"</div><br>");
			out.print("<div> 판매자의 답변 등록일 :" +boardRegTime+"</div><br>");
			
		} else { 
			
			
		}
		System.out.println(answerBoard);
		
		
		
		return null;
	}

}
