package member.account.action;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import action.Action;
import member.account.svc.CouponInfoService;
import member.account.svc.PointInfoService;
import vo.ActionForward;
import vo.MemberBean;

public class PointInfoAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
	ActionForward forward = null;
	int totalPoint =0;  //사용자 총 소지 포인트
		
		System.out.println("PointInfoAction");
		
		String uID=request.getParameter("uID");
		//포인트는 그냥 빈에 담아서 올것 리스트로 올 필요는 업승ㅁ
		
//		MemberBean memberBean = new MemberBean();
		PointInfoService pointInfoService= new PointInfoService();
		
		
		List<MemberBean> pointInfo=pointInfoService.getPointInfo(uID);
		
			for (MemberBean pointInfo2 : pointInfo) {
				if (pointInfo2.getPointAction()==1) {
					System.out.println("포인트 획득");
					totalPoint+=pointInfo2.getPointValue();
					System.out.println(totalPoint);
				}else {
					System.out.println("포인트 사용");
				}
			}
		

			
			
			
			
		
		request.setAttribute("pointInfo",pointInfo);
		request.setAttribute("totalPoint",totalPoint);
		forward = new ActionForward();
		forward.setPath("/member/pointInfo.jsp");
		
		return forward;
	}

}
