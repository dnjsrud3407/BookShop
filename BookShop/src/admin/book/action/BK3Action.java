package admin.book.action;

import java.io.PrintWriter;
import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import action.Action;
import admin.book.svc.BKService;
import vo.ActionForward;

public class BK3Action implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		// 출력 함수
    	response.setContentType("text/html; charset=UTF-8");
    	PrintWriter out = response.getWriter();
    	
    	//등록 시 사용하는 BK1, BK2
    	String BK1 = request.getParameter("BK1");
    	String BK2 = request.getParameter("BK2");
    	// 레벨 기본값
    	out.print("<option value='선택하세요'>선택하세요</option>");

    	//수정 시 사용하는 BeforeBK1, BeforeBK2
    	String BeforeBK1 = request.getParameter("BeforeBK1");
    	String BeforeBK2 = request.getParameter("BeforeBK2");
    	String BeforeBK3 = request.getParameter("BeforeBK3");

    	ArrayList<String> BKList = null;
    	BKService bKateService = new BKService(); 
    	BKList = bKateService.getBKList("BK1, BK2, BK3", "BK3");

    	if(BeforeBK1 == null && BeforeBK2 == null){		// 글 등록 시 수정
    		if(!BK1.equals("선택하세요") && !BK2.equals("선택하세요")){
    			// 대분류, 소분류 선택할 경우
    			for(int i=0;i<BKList.size();i+=3){
    				if(BK1.equals(BKList.get(i)) && BK2.equals(BKList.get(i+1))){
    					out.print("<option value='" + BKList.get(i+2) +"'>"+ BKList.get(i+2) +"</option>");
    				} 
    			}
    		}
    	} else {	// 글 수정 시
    		for(int i = 0; i < BKList.size(); i += 3){
    			// 수정하기 전 카테고리 가져와서 selected 상태로
    			if(BeforeBK1.equals(BKList.get(i)) && BeforeBK2.equals(BKList.get(i+1))){
    				if(BeforeBK3.equals(BKList.get(i+2))){
    					out.print("<option selected value='" + BKList.get(i+2) +"'>"+ BKList.get(i+2) +"</option>");
    				} else {
    					out.print("<option value='" + BKList.get(i+2) +"'>"+ BKList.get(i+2) +"</option>");
    				}
    			} 
    		}
    	}
		return null;
	}

}
