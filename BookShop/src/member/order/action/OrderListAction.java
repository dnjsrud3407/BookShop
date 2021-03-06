package member.order.action;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.mysql.fabric.xmlrpc.base.Member;

import action.Action;
import member.account.svc.CouponInfoService;
import member.account.svc.ModifyFormService;
import member.account.svc.PointInfoService;
import member.order.svc.OrderListService;
import vo.ActionForward;
import vo.MemberBean;
import vo.OrderBean;

public class OrderListAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		ActionForward forward = null;
		//주문목록 액션 페이지
		//마이페이지를 누르면 주문목록페이지가 뜬다
		OrderBean orderBean = new OrderBean();
		MemberBean memberinfo = new MemberBean();
		
		HttpSession session=request.getSession();
		String uId=(String) session.getAttribute("uID");

		OrderListService orderListService= new OrderListService();
		ModifyFormService modifyFormService =new ModifyFormService();
		 memberinfo=modifyFormService.getMemberInfo(uId);
		 
		List<MemberBean> couponList = new ArrayList<MemberBean>();
		List<OrderBean> orderList = new ArrayList<OrderBean>();
		List<OrderBean> deliveryList = new ArrayList<OrderBean>();
		List<OrderBean> orderList2 = new ArrayList<OrderBean>();
		List<OrderBean> orderCanCelReFundExCange = new ArrayList<OrderBean>();
		orderList=orderListService.getOrderList(uId);//주문목록
		orderList2=orderListService.getMypagePointInfo(uId);//포인트
		couponList=orderListService.getCouponList(uId);//쿠폰
		deliveryList=orderListService.getDeliveryList(uId);
		orderCanCelReFundExCange=orderListService.getorderCanCelReFundExCangeList(uId);
		
		for (OrderBean orderBean2 : orderList2) {
//			System.out.println("포인트 액수"+orderBean2.getPointValue());
		}
		
		
//--------------------총 포인트 가져오기---------------------------------------------
		
	PointInfoService pointInfoService= new PointInfoService();
		
			List<MemberBean> pointInfo=pointInfoService.getPointInfo(uId);
		
			int totalPoint =0; 
		
			for (MemberBean pointInfo2 : pointInfo) {
				
				if (pointInfo2.getPointAction()==1) {
//					System.out.println("포인트 사용");
					totalPoint+=pointInfo2.getPointValue();
//					System.out.println("사용한 포인트"+totalPoint);
	
				}else {
//					System.out.println("포인트 획득");
				}
			}
		
			request.setAttribute("totalPoint",totalPoint);
			
//----------------보유 쿠폰 갯수 가져오기 (사용가능만!)-----------------------------
	Date stardateDate=null;
	Date enddateDate=null;
	CouponInfoService couponInfoService= new CouponInfoService();		
	List<MemberBean> couponInfo =  new ArrayList<MemberBean>(); //one
	
	couponInfo =couponInfoService.getCouponInfo(uId);
	
  	  int couponCount=0;
	
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
		
		if (memberBean.getCouponAction()==1) {///사용
			memberBean.setCouponStatus("사용");
		
			
		}else if(memberBean.getCouponAction()==0){ //사용가능
			memberBean.setCouponStatus("사용안함");
			couponCount+=1;
		}
		
		if (result>0) { //쿠폰기한 지났을때
			memberBean.setCouponStatus("만료");
			memberBean.setCouponAction(2);
		}
		
	}//for 문 끝
	
	System.out.println("쓸 수 있는 쿠폰 수"+couponCount);
	request.setAttribute("couponInfo",couponInfo); //상단에 표시되는 개수 
	request.setAttribute("couponRealCount",couponCount); //상단에 표시되는 개수 
	
	//---------------------------------------배송중 -------------------------------------------		
	
	int delivertcount=0;
	for (OrderBean deliveryList2 : deliveryList) {
				delivertcount+=1;
	}
	request.setAttribute("delivertcount",delivertcount); // 배송중카운트
	//---------------------------------------교환반품-------------------------------------------		
	
		int orderReFundExCangecount=0;
		for (OrderBean orderCanCelReFundExCange2 : orderCanCelReFundExCange) {
			orderReFundExCangecount+=1;
		}
		request.setAttribute("orderReFundExCangecount",orderReFundExCangecount); // 배송중카운트
//---------------------------------------주문 관련-------------------------------------------	
		int bookPrice=0;
		int orderEA=0;
		int pointValue=0; //포인트는 따로조회
		int couponValue=0;
		int deliveryCost=2500; //배송비 고정
		int total = 0;
		

		
		
			
			for (int i = 0; i < orderList.size(); i++) {
				 bookPrice=orderList.get(i).getBookPrice();
				 orderEA=orderList.get(i).getBookEA();
				 couponValue=orderList.get(i).getVolume();
//				 System.out.println("책금액+책갯수+사용한 쿠폰금액"+bookPrice+","+orderEA+","+couponValue);
				 
				 // 만약 couponAction == 0 이라면  -하면안된다
				 if (orderList.get(i).getCouponAction()==0) {
					 orderList.get(i).setVolume(0);
					 orderList.get(i).setCoupon_name("");
				}else {
					total=bookPrice*orderEA-couponValue+deliveryCost;
				}//if문 끝
//				 System.out.println("총금액"+total);
//				 request.setAttribute("total", total);
			}//for 문 끝
			request.setAttribute("orderList",orderList); //주문정보
			request.setAttribute("couponList",couponList); //쿠폰
			request.setAttribute("orderList2",orderList2); // 포인트s
			request.setAttribute("memberinfo",memberinfo); //멤버정보
			request.setAttribute("deliveryList",deliveryList); //배송중
			request.setAttribute("orderCanCelReFundExCange",orderCanCelReFundExCange); //교환취소반품
			
			System.out.println("멤버등급찍기"+memberinfo.getGrade());
			
		
		forward = new ActionForward();
		forward.setPath("mypage.jsp");
		return forward;
	
	}

}
