package admin.book.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import action.Action;
import admin.book.svc.BuyProService;
import vo.ActionForward;

public class BuyProAction implements Action {

    @Override
    public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
        ActionForward forward = null;
        BuyProService buyProService = new BuyProService();
        buyProService.buy();

        forward = new ActionForward();
        forward.setPath("BuyList.abook");
        forward.setRedirect(true);
        
        return forward;
    }

}
