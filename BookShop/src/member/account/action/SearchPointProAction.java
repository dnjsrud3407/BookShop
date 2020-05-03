package member.account.action;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import action.Action;
import member.account.svc.PointInfoService;
import member.account.svc.SearchPointProService;
import vo.ActionForward;
import vo.MemberBean;

public class SearchPointProAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		ActionForward forward = null;
		
		System.out.println("SearchPointProAction");
		
		
		HttpSession session=request.getSession();
		String uID=(String) session.getAttribute("uID");
		String startDate = request.getParameter("searchStartDate");
		String endDate = request.getParameter("searchEndDate");
		
		System.out.println("프로액션"+startDate+","+endDate);
		
		
		SearchPointProService searchPointProService= new SearchPointProService();
		List<MemberBean> pointInfo=searchPointProService.getPointInfo(uID,startDate,endDate);
		
		PointInfoService pointInfoService= new PointInfoService();
		List<MemberBean> pointInfoTotal=pointInfoService.getPointInfo(uID);
		
		int totalPoint =0;
		for (MemberBean pointInfo2 : pointInfoTotal) {
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
