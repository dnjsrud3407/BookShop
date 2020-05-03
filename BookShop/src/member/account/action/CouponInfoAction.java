package member.account.action;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import action.Action;
import member.account.svc.CouponInfoService;
import vo.ActionForward;
import vo.MemberBean;
import vo.PageInfo;

public class CouponInfoAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		ActionForward forward = null;
//		String startDate=null;
//		String endDate=null;
		Date stardateDate=null;
		Date enddateDate=null;
		
		
		
		
		//---------------------------------------------------------페이징---------------------------------------------------------------------------------------
//		int page = 1; // 현재 페이지 번호
//		int limit = 5; // 한 페이지 당 출력할 게시물 수
		
		System.out.println("CouponInfoAction");
		HttpSession session = request.getSession();
		String uID=(String) session.getAttribute("uID");
		
		
//		
//		if(request.getParameter("page") != null) {
//			page = Integer.parseInt(request.getParameter("page")); // 정수로 변환하여 저장
//		}
//		
//	
//		
		CouponInfoService couponInfoService= new CouponInfoService();
//		int listCount = couponInfoService.getCouponInfoListCount(uID);
//		System.out.println("쿠폰카운트쿠폰액션  ㅣ "+listCount);
//		System.out.println("현재페이지 ; "+page);	
//		
		
		
		
//		
//		int maxPage = (int)((double)listCount / limit + 0.95);
//		// 2. 시작 페이지 번호 계산
//		int startPage = (((int)((double)page / 10 + 0.9)) - 1) * 10 + 1;
//		// 3. 마지막 페이지 번호 계산
//		int endPage = startPage + 10 - 1;
//		
//		if(endPage > maxPage) {
//			endPage = maxPage;
//		}
//		
//		PageInfo pageInfo = new PageInfo(page, maxPage, startPage, endPage, listCount);
//		
		
		
//		int startRow=((page-1)*limit)+1;
	
		
		List<MemberBean> couponInfo =  new ArrayList<MemberBean>(); //one
		
		
		couponInfo =couponInfoService.getCouponInfo(uID);
		
	  	  
		
		for (MemberBean memberBean : couponInfo) {
			
			//쿠폰의 시작날짜와 끝나는 날짜를 가져온다
			stardateDate=memberBean.getCouponReg_date();
			enddateDate=memberBean.getCouponEnd_date();
			
			//현제 시스템 날짜를 가져온다
			Calendar calendar = Calendar.getInstance();
			java.util.Date date = calendar.getTime();
			
			//가져온 현재 날짜를 String으로 변환,format으로 형식맞춰줌
			String today = (new SimpleDateFormat("yyyy-MM-dd-HH:mm:ss").format(date));
			
			//쿠폰의 시작날짜와 끝나는 날짜를 String으로 변환 format으로 형식 맞춰줌
			String startDate = new SimpleDateFormat("yyyy-mm-dd-hh:mm:ss").format(stardateDate);
			String endDate = new SimpleDateFormat("yyyy-mm-dd-hh:mm:ss").format(enddateDate);
			
			//compareTo 앞에있는 오늘날짜가 끝나는 날짜보다 크 다면 result는 0보다 큰 숫자가 된다
			int result = today.compareTo(endDate);
			
			if (memberBean.getCouponAction()==1) {//쿠폰사용시
				memberBean.setCouponStatus("사용");
				
			}else if(memberBean.getCouponAction()==0){ //사용하지 않고 만료되지않은 살아있는 쿠폰
				memberBean.setCouponStatus("사용안함");
			}
			
			if (result>0) { //쿠폰기한 지났을때
				memberBean.setCouponStatus("만료");
				memberBean.setCouponAction(2);
			}
			
			
			
		}//for 문 끝
		
		
		
		
		  for (MemberBean memberBean : couponInfo) {
			System.out.println("--*현재쿠폰상태==="+memberBean.getCouponStatus());
			System.out.println(memberBean.getCoupon_name());
		}


	  
		
//		request.setAttribute("pageInfo", pageInfo);
		request.setAttribute("couponInfo",couponInfo);
		forward = new ActionForward();
		forward.setPath("/member/couponInfo.jsp");
		
		return forward;
	}

}
