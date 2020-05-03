package admin.member.action;

import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import action.Action;
import admin.member.svc.MainBoardService;
import vo.ActionForward;
import vo.BoardBean;

public class MainBoardAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		int kID = 0; 
		if(request.getParameter("type") != null || request.getParameter("type") != "") {
			kID = Integer.parseInt(request.getParameter("type"));
		}
		
		int page = 1; int limit = 5;
		MainBoardService mainBoardService = new MainBoardService();
		ArrayList<BoardBean> boardList = null;
		
		// 출력 함수 
    	response.setContentType("text/html; charset=UTF-8");
    	PrintWriter out = response.getWriter();
    	out.print("<tr>");
    	
    	boardList = mainBoardService.getBoardList(kID, page, limit);
    	if(kID == 102) {	// 상품문의일 경우
			out.print("<td class=\"selectColor\" onclick=\"getBoard(102)\">상품문의</td>");
	    	out.print("<td class=\"boardTitle\" onclick=\"getBoard(103)\">상품후기</td>");
	    	out.print("<td class=\"boardTitle\" onclick=\"getBoard(109)\">1:1문의</td>");
		} else if(kID == 103) {	// 상품후기일 경우
			out.print("<td class=\"boardTitle\" onclick=\"getBoard(102)\">상품문의</td>");
	    	out.print("<td class=\"selectColor\" onclick=\"getBoard(103)\">상품후기</td>");
	    	out.print("<td class=\"boardTitle\" onclick=\"getBoard(109)\">1:1문의</td>");
		} else {	// 1:1문의일 경우
			out.print("<td class=\"boardTitle\" onclick=\"getBoard(102)\">상품문의</td>");
	    	out.print("<td class=\"boardTitle\" onclick=\"getBoard(103)\">상품후기</td>");
	    	out.print("<td class=\"selectColor\" onclick=\"getBoard(109)\">1:1문의</td>");
		}
		
    	out.print("</tr>");
    	out.print("<tr></tr>");

    	
    	for (int i = 0; i < boardList.size(); i++) {
    		//가져온 현재 날짜를 String으로 변환,format으로 형식맞춰줌
    		String date = (new SimpleDateFormat("yyyy-MM-dd").format(boardList.get(i).getBoardRegTime()));
    		out.print("<tr>");
    		if(kID == 102) {	// 상품문의일 경우
    			out.print("<td colspan=\"2\"><a href='QWriteForm.abook?boardNum="+ boardList.get(i).getBoardNum() +"&page=1'>"+boardList.get(i).getBoardTitle()+"</a></td>");
    		} else if(kID == 102) {	// 상품후기일 경우
    			out.print("<td colspan=\"2\"><a href='ReviewWriteForm.abook?boardNum="+ boardList.get(i).getBoardNum() +"&page=1'>"+boardList.get(i).getBoardTitle()+"</a></td>");
    		} else {	// 1:1문의일 경우
    			out.print("<td colspan=\"2\"><a href='QDetail.adb?boardNum="+ boardList.get(i).getBoardNum() +"'>"+boardList.get(i).getBoardTitle()+"</a></td>");
    		}
    		out.print("<td>"+date+"</td>");
    		out.print("</tr>");
		}
    	
		return null;
	}

}
