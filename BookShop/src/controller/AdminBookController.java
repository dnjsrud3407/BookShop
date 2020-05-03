package controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import action.Action;
import admin.book.action.BK1Action;
import admin.book.action.BK2Action;
import admin.book.action.BK3Action;
import admin.book.action.BuyListAction;
import admin.book.action.BuyProAction;
import admin.book.action.DeleteFormAction;
import admin.book.action.DeleteProAction;
import admin.book.action.DetailAction;
import admin.book.action.ListAction;
import admin.book.action.ModifyFormAction;
import admin.book.action.ModifyProAction;
import admin.book.action.QDeleteFormAction;
import admin.book.action.QDeleteProAction;
import admin.book.action.QDetailAction;
import admin.book.action.QListAction;
import admin.book.action.QModifyProAction;
import admin.book.action.QSearchProAction;
import admin.book.action.QWriteFormAction;
import admin.book.action.QWriteProAction;
import admin.book.action.RefundListAction;
import admin.book.action.RefundProAction;
import admin.book.action.ReviewDeleteFormAction;
import admin.book.action.ReviewDeleteProAction;
import admin.book.action.ReviewDetailAction;
import admin.book.action.ReviewListAction;
import admin.book.action.ReviewModifyProAction;
import admin.book.action.ReviewSearchProAction;
import admin.book.action.ReviewWriteFormAction;
import admin.book.action.ReviewWriteProAction;
import admin.book.action.SearchProAction;
import admin.book.action.WriteProAction;
import vo.ActionForward;

@WebServlet("*.abook")
public class AdminBookController extends HttpServlet {
protected void doProcess(HttpServletRequest request, HttpServletResponse response) throws ServletException,IOException {
        
        request.setCharacterEncoding("utf-8");
        
        String command = request.getServletPath();
        System.out.println(command);
        
        Action action = null;
        ActionForward forward = null;
        
        // 서블릿 주소에 따라 각각 다른 작업을 수행
        if(command.equals("/List.abook")) { // --- 제품 목록(수정 / 삭제 / 검색 가능)
            // 제품 목록 ListAction()
            action = new ListAction();
            try {
                forward = action.execute(request, response);
            } catch (Exception e) {
                e.printStackTrace();
            }
        } else if(command.equals("/BK1.abook")) { 
            // 제품 대분류 카테고리 ListAction()
            action = new BK1Action();
            try {
                forward = action.execute(request, response);
            } catch (Exception e) {
                e.printStackTrace();
            }
        } else if(command.equals("/BK2.abook")) { 
            // 제품 레벨 카테고리 ListAction()
            action = new BK2Action();
            try {
                forward = action.execute(request, response);
            } catch (Exception e) {
                e.printStackTrace();
            }
        } else if(command.equals("/BK3.abook")) { 
            // 제품 소분류 카테고리 ListAction()
            action = new BK3Action();
            try {
                forward = action.execute(request, response);
            } catch (Exception e) {
                e.printStackTrace();
            }
        } else if(command.equals("/Detail.abook")) {
            // --- 제품 상세보기
            action = new DetailAction();
            try {
                forward = action.execute(request, response);
            } catch (Exception e) {
                e.printStackTrace();
            }
        } else if(command.equals("/ModifyForm.abook")) {
            // --- 제품 수정 폼 (관리자가 작성한 내용 불러옴)
            action = new ModifyFormAction();
            try {
                forward = action.execute(request, response);
            } catch (Exception e) {
                e.printStackTrace();
            }
        } else if(command.equals("/ModifyPro.abook")) {
            // --- 제품 수정 작업 
            action = new ModifyProAction();
            try {
                forward = action.execute(request, response);
            } catch (Exception e) {
                e.printStackTrace();
            }
        } else if(command.equals("/DeleteForm.abook")) {
            // --- 제품 삭제 폼
        	action = new DeleteFormAction();
            try {
                forward = action.execute(request, response);
            } catch (Exception e) {
                e.printStackTrace();
            }
        } else if(command.equals("/DeletePro.abook")) {
            // --- 제품 삭제 작업 
            action = new DeleteProAction();
            try {
                forward = action.execute(request, response);
            } catch (Exception e) {
                e.printStackTrace();
            }
        } else if(command.equals("/Search.abook")) {
            // --- 제품 검색 작업 
            action = new SearchProAction();
            try {
                forward = action.execute(request, response);
            } catch (Exception e) {
                e.printStackTrace();
            }
        } else if(command.equals("/WriteForm.abook")) { // --- 제품 등록
            // --- 제품 등록 폼 
            forward = new ActionForward();
            forward.setPath("./admin/book/writeForm.jsp");
        } else if(command.equals("/WritePro.abook")) {
            // --- 제품 작성 작업 WriteProAction()
            action = new WriteProAction();
            try {
                forward = action.execute(request, response);
            } catch (Exception e) {
                e.printStackTrace();
            }
        } else if(command.equals("/QList.abook")) { // --- 상품 문의
            // 상품 문의 목록 QListAction()
            action = new QListAction();
            try {
                forward = action.execute(request, response);
            } catch (Exception e) {
                e.printStackTrace();
            }
        } else if(command.equals("/QWriteForm.abook")) {
            // --- 상품 문의 답변 작성 폼 (사용자가 작성한 내용을 불러와야 함)
            action = new QWriteFormAction();
            try {
                forward = action.execute(request, response);
            } catch (Exception e) {
                e.printStackTrace();
            }
        } else if(command.equals("/QWritePro.abook")) {
            // --- 상품 문의 작성 작업 QWriteProAction()
            action = new QWriteProAction();
            try {
                forward = action.execute(request, response);
            } catch (Exception e) {
                e.printStackTrace();
            }
        } else if(command.equals("/QDetail.abook")) {
            // 상품 문의 답변 상세보기
            action = new QDetailAction();
            try {
                forward = action.execute(request, response);
            } catch (Exception e) {
                e.printStackTrace();
            }
        } else if(command.equals("/QModifyPro.abook")) {
            // 상품 문의 글 수정 작업 
        	System.out.println("QModifyPro.abookcontroller");
            action = new QModifyProAction();
            try {
                forward = action.execute(request, response);
            } catch (Exception e) {
                e.printStackTrace();
            }
        } else if(command.equals("/QSearchPro.abook")) {
            // 상품 문의 글 검색 작업 
            action = new QSearchProAction();
            try {
                forward = action.execute(request, response);
            } catch (Exception e) {
                e.printStackTrace();
            }
        } else if(command.equals("/QDeleteForm.abook")) {
            // 상품 문의 글 삭제 폼
            action = new QDeleteFormAction();
            try {
                forward = action.execute(request, response);
            } catch (Exception e) {
                e.printStackTrace();
            }
        } else if(command.equals("/QDeletePro.abook")) {
            // 상품 문의 답변 글 삭제 작업
            action = new QDeleteProAction();
            try {
                forward = action.execute(request, response);
            } catch (Exception e) {
                e.printStackTrace();
            }
        } else if(command.equals("/ReviewList.abook")) { // --- 상품 후기
            // 상품 후기 목록 ReviewListAction()
            action = new ReviewListAction();
            try {
                forward = action.execute(request, response);
            } catch (Exception e) {
                e.printStackTrace();
            }
        } else if(command.equals("/ReviewWriteForm.abook")) {
            // --- 상품 후기 답변 작성 폼 (사용자가 작성한 내용을 불러와야 함)
            action = new ReviewWriteFormAction();
            try {
                forward = action.execute(request, response);
            } catch (Exception e) {
                e.printStackTrace();
            }
        } else if(command.equals("/ReviewWritePro.abook")) {
            // --- 상품 후기 작성 작업 ReviewWriteProAction()
            action = new ReviewWriteProAction();
            try {
                forward = action.execute(request, response);
            } catch (Exception e) {
                e.printStackTrace();
            }
        } else if(command.equals("/ReviewDetail.abook")) {
            // 상품 후기 답변 상세보기
            action = new ReviewDetailAction();
            try {
                forward = action.execute(request, response);
            } catch (Exception e) {
                e.printStackTrace();
            }
        } else if(command.equals("/ReviewModifyPro.abook")) {
            // 상품 후기 글 수정 작업 
            action = new ReviewModifyProAction();
            try {
                forward = action.execute(request, response);
            } catch (Exception e) {
                e.printStackTrace();
            }
        } else if(command.equals("/ReviewSearchPro.abook")) {
            // 상품 후기 글 검색 작업 
            action = new ReviewSearchProAction();
            try {
                forward = action.execute(request, response);
            } catch (Exception e) {
                e.printStackTrace();
            }
        } else if(command.equals("/ReviewDeleteForm.abook")) {
        	// 상품 후기 글 삭제 폼
            action = new ReviewDeleteFormAction();
            try {
                forward = action.execute(request, response);
            } catch (Exception e) {
                e.printStackTrace();
            }
        } else if(command.equals("/ReviewDeletePro.abook")) {
            // 상품 후기 답변 글 삭제 작업
            action = new ReviewDeleteProAction();
            try {
                forward = action.execute(request, response);
            } catch (Exception e) {
                e.printStackTrace();
            }
        } else if(command.equals("/RefundList.abook")) {   // 상품 반품 하기
            // 상품 반품할 리스트 불러오기
            action = new RefundListAction();
            try {
                forward = action.execute(request, response);
            } catch (Exception e) {
                e.printStackTrace();
            }
        } else if(command.equals("/RefundPro.abook")) { 
            // 상품 하기 작업
            action = new RefundProAction();
            try {
                forward = action.execute(request, response);
            } catch (Exception e) {
                e.printStackTrace();
            }
        } else if(command.equals("/BuyList.abook")) {   // 물량 추가 하기
            // 물량 추가할 리스트 불러오기
            action = new BuyListAction();
            try {
                forward = action.execute(request, response);
            } catch (Exception e) {
                e.printStackTrace();
            }
        } else if(command.equals("/BuyPro.abook")) { 
            // 물량 추가 작업
            action = new BuyProAction();
            try {
                forward = action.execute(request, response);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    
        

        //=================================================================================
        // ActionForward 객체의 포워딩 방식
        if (forward != null) {
            if (forward.isRedirect()) {
                response.sendRedirect(forward.getPath());
            }else {
                RequestDispatcher dispatcher = request.getRequestDispatcher(forward.getPath());
                dispatcher.forward(request, response);
            }
        }else {
            System.out.println("ActionFoward 이 null입니다");
        }
    }
    
    
    
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doProcess(request,response);
    }


    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doProcess(request,response);
    }
}
