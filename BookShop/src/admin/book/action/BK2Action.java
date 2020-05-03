package admin.book.action;

import java.io.PrintWriter;
import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import action.Action;
import admin.book.svc.BKService;
import vo.ActionForward;

public class BK2Action implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		// 출력 함수
    	response.setContentType("text/html; charset=UTF-8");
    	PrintWriter out = response.getWriter();
		
		// 등록 시 사용하는 BK1
		String BK1 = request.getParameter("BK1");
		// 소분류 기본값
		out.print("<option value='선택하세요'>선택하세요</option>");

		// 수정 시 사용하는 BeforeBK1, BeforeBK2
		String BeforeBK1 = request.getParameter("BeforeBK1");
		String BeforeBK2 = request.getParameter("BeforeBK2");

		ArrayList<String> BKList = null;
		BKService bKateService = new BKService(); 
		BKList = bKateService.getBKList("BK1, BK2", "BK2");
		if(BeforeBK1 == null && BeforeBK2 == null){		// 글 등록 시
			// 대분류 선택할 경우
			if(!BK1.equals("선택하세요")){
				for(int i=0;i<BKList.size();i+=2){
					if(BK1.equals(BKList.get(i))){
						out.print("<option value='"+ BKList.get(i+1) +"'>"+ BKList.get(i+1) +"</option>");
					} 
				}
			}
		} else {	// 글 수정 시 수정
			for(int i=0;i<BKList.size();i+=2){
				// 수정하기 전 카테고리 가져와서 selected 상태로
				if(BeforeBK1.equals(BKList.get(i))){
					if(BeforeBK2.equals(BKList.get(i+1))){
						out.print("<option selected value='"+ BKList.get(i+1) +"'>"+ BKList.get(i+1) +"</option>");
					} else {
						out.print("<option value='"+ BKList.get(i+1) +"'>"+ BKList.get(i+1) +"</option>");
					}
				} 
			}
		}
		return null;
	}

}
