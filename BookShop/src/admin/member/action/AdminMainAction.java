package admin.member.action;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import action.Action;
import admin.member.svc.MainBoardService;
import admin.sales.svc.OrderCompListService;
import vo.ActionForward;
import vo.BoardBean;
import vo.OrderBean;

public class AdminMainAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		ActionForward forward = null;
		
		int page = 1; int limit = 5; int kID = 0;
		MainBoardService adminMainService = new MainBoardService();
		// 상품 가져오기
		ArrayList<Integer> bookEAList = adminMainService.getBookEA();

		String orderStatus = ""; 
		int cashe = 0;
		OrderCompListService orderCompListService = new OrderCompListService();
		
		// 주문현황 가져오기
		ArrayList<Integer> orderList = new ArrayList<Integer>();
		orderStatus = "결제완료";
		orderList.add(orderCompListService.getOrderCount(orderStatus));
		orderStatus = "배송중";
		orderList.add(orderCompListService.getOrderCount(orderStatus));
		orderStatus = "배송완료";
		orderList.add(orderCompListService.getOrderCount(orderStatus));
		
		
		// =================================달별 매출현황====================================
		
		// 출력할 월 정보 (12개) : 이번달-1 ~ 작년 이번달까지 (출력하는 것과 값이 거꾸로 나온다) -> jsp 에서 값 바꿔줌
		// ex) 현재 4월
		// 3,2,1,12,11,10,9,8,7,6,5,4
		ArrayList<Integer> monthList = new ArrayList<Integer>();
		Calendar cal = Calendar.getInstance();
		for (int i = 0; i < 12; i++) {
			if ((cal.get(cal.MONTH)-i) > 0) {
				monthList.add((cal.get(cal.MONTH)-i));
			} else {
				monthList.add(12 + (cal.get(cal.MONTH)-i));
			}
		}
		
		
		// 달별 매출 현황 (출력하는 것과 값이 거꾸로 나온다) -> jsp 에서 값 바꿔줌
		ArrayList<Integer> monthCasheList = new ArrayList<Integer>();
		int year = cal.get(cal.YEAR);
		int monthTotal = 0;
		for (int i = 0; i < monthList.size(); i++) {
			if (monthList.get(i) == 12) {
				year -= 1;
			}
			monthTotal = orderCompListService.orderComplList(year, monthList.get(i));
			monthCasheList.add(monthTotal);
		}
		
		for (int i = 0; i < monthList.size(); i++) {
			System.out.println(monthList.get(i) + " : " + monthCasheList.get(i));
		}
		 	
		
		// ================================================================================
		// ============================ 매출현황
		ArrayList<Integer> salesCasheList = new ArrayList<Integer>();

		// -- 총 금액 구하기
		orderStatus = "";		
		cashe = orderCompListService.orderComplList(orderStatus);
		salesCasheList.add(cashe);
		
		// -- 취소/반품 금액 구하기
		int cancelCashe = 0;
		orderStatus = "취소완료";		
		cancelCashe = orderCompListService.orderComplList(orderStatus);

		orderStatus = "반품완료";		
		cancelCashe += orderCompListService.orderComplList(orderStatus);
		salesCasheList.add(cancelCashe);
		
		// -- 매출액(총 금액-cancelCashe) 금액 구하기
		salesCasheList.add(cashe - cancelCashe);
		// ================================================================================
		
			
		// 공지사항 5개
		kID = 100;
		ArrayList<BoardBean> noticeList = adminMainService.getNaEBoardList(kID, page, limit);
		// 이벤트 5개
		kID = 101;
		ArrayList<BoardBean> eventList = adminMainService.getNaEBoardList(kID, page, limit);
		// 답변 게시글 - 상품문의
		kID = 102;
		ArrayList<BoardBean> qList = adminMainService.getBoardList(kID, page, limit);
		
		
		// request 파라미터 넘기기
		request.setAttribute("monthList", monthList);
		request.setAttribute("monthCasheList", monthCasheList);
		request.setAttribute("orderList", orderList);
		request.setAttribute("salesCasheList", salesCasheList);
		request.setAttribute("bookEAList", bookEAList);
		request.setAttribute("noticeList", noticeList);
		request.setAttribute("eventList", eventList);
		request.setAttribute("qList", qList);
		forward = new ActionForward();
		forward.setPath("/admin/adminMain.jsp");
		
		return forward;
	}

}
