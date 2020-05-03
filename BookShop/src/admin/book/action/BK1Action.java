package admin.book.action;

import java.io.PrintWriter;
import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import action.Action;
import admin.book.svc.BKService;
import vo.ActionForward;

public class BK1Action implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		// 출력 함수
    	response.setContentType("text/html; charset=UTF-8");
    	PrintWriter out = response.getWriter();
    	
		String BeforeBK1 = request.getParameter("BeforeBK1");
		//소분류 기본값
		out.print("<option value='선택하세요'>선택하세요</option>");
		ArrayList<String> BKList = null;
		BKService bKService = new BKService(); 
		BKList = bKService.getBKList("BK1", "BK1");

		// 등록하는 경우
		if(BeforeBK1 == null){
			for(int i = 0; i < BKList.size(); i++){
				out.print("<option value='"+ BKList.get(i) +"'>"+ BKList.get(i) +"</option>");
			}
		} else {	// 수정하는 경우
			for(int i=0;i<BKList.size();i++){
				// 수정하기 전 카테고리 가져와서 selected 상태로
				if(BeforeBK1.equals(BKList.get(i))){	
					out.print("<option selected value='"+ BKList.get(i) +"'>"+ BKList.get(i) +"</option>");
				} else {
					out.print("<option value='"+ BKList.get(i) +"'>"+ BKList.get(i) +"</option>");
				}
			}
		}
		
		return null;
	}

}
