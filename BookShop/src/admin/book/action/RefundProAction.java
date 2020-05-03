package admin.book.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import action.Action;
import admin.book.svc.RefundProService;
import vo.ActionForward;

public class RefundProAction implements Action {

    @Override
    public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
        ActionForward forward = null;
        RefundProService refundProService = new RefundProService();
        refundProService.refund();

        forward = new ActionForward();
        forward.setPath("RefundList.abook");
        forward.setRedirect(true);
        
        return forward;
    }

}
