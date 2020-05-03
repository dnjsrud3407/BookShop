package admin.book.action;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import action.Action;
import admin.book.svc.BuyListService;
import vo.ActionForward;

public class BuyListAction implements Action {

    @Override
    public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
        ActionForward forward = null;
//      System.out.println("물량 추가할 내역 보기");
        
        BuyListService buyListService = new BuyListService();
        ArrayList list = buyListService.getList();
        
        forward = new ActionForward();
        forward.setPath("./admin/book/buyList.jsp");
        
        return forward;
    }

}
