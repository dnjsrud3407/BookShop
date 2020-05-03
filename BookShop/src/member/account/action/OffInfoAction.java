package member.account.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import action.Action;
import member.account.svc.OffInfoService;
import vo.ActionForward;

public class OffInfoAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		ActionForward forward = null;
		System.out.println("OffInfoAction");
		
		//����Ʈ�� ���� ��ȸ�Ϸ� ����
		OffInfoService OffInfoService = new  OffInfoService();
		
		
		forward = new ActionForward();
		forward.setPath("/member/offInfo.jsp");
		
		return forward;
	}

}
