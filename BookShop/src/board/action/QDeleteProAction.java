package board.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import action.Action;
import board.svc.QDeleteProService;
import vo.ActionForward;

public class QDeleteProAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		ActionForward forward = null;
		int result=0;
		System.out.println("1:1 QDeleteProAction");
		int boardNum=Integer.parseInt(request.getParameter("boardNum"));
		int kID=Integer.parseInt(request.getParameter("kID"));
		QDeleteProService q_DeleteProService = new QDeleteProService();
		result=q_DeleteProService.deleteOneOnOne(boardNum,kID);
		
		
		
		
		
		forward = new ActionForward();
		forward.setPath("QList.bo");
		forward.setRedirect(true);
		return forward;
	}

}
