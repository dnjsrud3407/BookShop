package admin.book.action;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import action.Action;
import admin.book.svc.RefundListService;
import vo.ActionForward;

public class RefundListAction implements Action {

    @Override
    public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
        ActionForward forward = null;
//      System.out.println("상품 반품할 내역 보기");
        
        RefundListService refundListService = new RefundListService();
        ArrayList list = refundListService.getList();
        
        forward = new ActionForward();
        forward.setPath("./admin/book/refundList.jsp");
        
        return forward;
    }

}
