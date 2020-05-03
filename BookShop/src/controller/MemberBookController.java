package controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


import action.Action;
import member.account.action.MiddleBookAction;
import member.book.action.BookBuyAction;
import member.book.action.BookBuyAction2;
import member.book.action.BookBuyProAction;
import member.book.action.BookBuyProAction2;
import member.book.action.CartAddAction;
import member.book.action.CartAddAction2;
import member.book.action.CartListAction;
import member.book.action.CartQtyChangeAction;
import member.book.action.CartRemoveAction;
import member.book.action.BookDetailAction;
import member.book.action.BookLikeProAction;
import member.book.action.BookListALLAcion;
import member.book.action.BookListAcion;
import member.book.action.BookSearchListAcion;
import member.book.action.QDeleteProAction;
import member.book.action.QDetailProAcion;
import member.book.action.QListAcion;
import member.book.action.QModifyFormAction;
import member.book.action.QModifyProAcion;
import member.book.action.QWriteProAcion;
import member.book.action.ReviewDeleteProAction;
import member.book.action.ReviewDetailProAcion;
import member.book.action.ReviewListAcion;
import member.book.action.ReviewModifyFormAction;
import member.book.action.ReviewModifyProAcion;
import member.book.action.ReviewWriteProAcion;
//import member.book.action.BookBuyProAction;
//import member.book.action.BookCartProAction;
//import member.book.action.BookLikeProAction;
//import member.book.action.BookListAcion;
//import member.book.action.QDeleteProAction;
//import member.book.action.QDetailProAcion;
//import member.book.action.QListAcion;
//import member.book.action.QModifyFormAction;
//import member.book.action.QModifyProAcion;
//import member.book.action.QWriteProAcion;
//import member.book.action.ReviewDeleteProAction;
//import member.book.action.ReviewDetailProAcion;
//import member.book.action.ReviewListAcion;
//import member.book.action.ReviewModifyFormAction;
//import member.book.action.ReviewModifyProAcion;
//import member.book.action.ReviewWriteProAcion;
import vo.ActionForward;


@WebServlet("*.book")
public class MemberBookController extends HttpServlet {       
	protected void doProcess(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		
		String command = request.getServletPath();
		System.out.println("BFC - command : " + command);
		
		Action action = null;
		ActionForward forward = null;
		
		// === 메인에서 ajax 제어 작업
		if(command.equals("/MiddleBook.book")) {
			action = new MiddleBookAction();
			try {
				forward = action.execute(request, response);
			} catch (Exception e) {
				e.printStackTrace();
			}
		} else if(command.equals("/BookList.book")) {
			action = new BookListAcion();
			try {
				forward = action.execute(request, response);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}else if(command.equals("/BookListALL.book")) {
			action = new BookListALLAcion();
			try {
				forward = action.execute(request, response);
			} catch (Exception e) {
				e.printStackTrace();
			}
		} else if(command.equals("/BookSearchList.book")) {
			action = new BookSearchListAcion();
			try {
				forward = action.execute(request, response);
			} catch (Exception e) {
				e.printStackTrace();
			}
		} else if(command.equals("/Book.book")) {
			action = new BookDetailAction();
			try {
				forward = action.execute(request, response);
			} catch (Exception e) {
				e.printStackTrace();
			}	
		} else if(command.equals("/ReviewWriteForm.book")) {
			forward = new ActionForward();
			forward.setPath("./book/review_write.jsp");
			
		} else if(command.equals("/ReviewWritePro.book")) {
			action = new ReviewWriteProAcion();
			try {
				forward = action.execute(request, response);
			} catch (Exception e) {
				e.printStackTrace();
			}
			
		} else if(command.equals("/ReviewModifyForm.book")) {
			action = new ReviewModifyFormAction();
			try {
				forward = action.execute(request, response);
			} catch (Exception e) {
				e.printStackTrace();
			}
		} else if(command.equals("/ReviewModifyPro.book")) {
			action = new ReviewModifyProAcion();
			try {
				forward = action.execute(request, response);
			} catch (Exception e) {
				e.printStackTrace();
			}
		} else if(command.equals("/ReviewDetail.book")) {
			action = new ReviewDetailProAcion();
			try {
				forward = action.execute(request, response);
			} catch (Exception e) {
				e.printStackTrace();
			} 
			
		} else if(command.equals("/ReviewDeleteForm.book")){
			forward = new ActionForward();
			forward.setPath("./book/review_delete.jsp");
			
		} else if(command.equals("/ReviewDeletePro.book")) {
			action = new ReviewDeleteProAction();
			try {
				forward = action.execute(request, response);
			} catch (Exception e) {
				e.printStackTrace();
			}
			
		} else if(command.equals("/ReviewList.book")) {
			action = new ReviewListAcion();
			try {
				forward = action.execute(request, response);
			} catch (Exception e) {
				e.printStackTrace();
			}
		// Questions >>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>
		} else if(command.equals("/QList.book")) {
			action = new QListAcion();
			try {
				forward = action.execute(request, response);
			} catch (Exception e) {
				e.printStackTrace();
			}
		// 상품문의 폼	
		} else if(command.equals("/QWriteForm.book")) {
			forward = new ActionForward();
			forward.setPath("./book/Q_write.jsp");
		
		// 상품문의 글쓰기	
		} else if(command.equals("/QWritePro.book")) {
			action = new QWriteProAcion();
			try {
				forward = action.execute(request, response);
			} catch (Exception e) {
				e.printStackTrace();
			}
		
		// 상품문의 수정 폼
		} else if(command.equals("/QModifyForm.book")) {
			action = new QModifyFormAction();
			try {
				forward = action.execute(request, response);
			} catch (Exception e) {
				e.printStackTrace();
			}
		
		// 상품문의 글수정
		} else if(command.equals("/QModifyPro.book")) {
			action = new QModifyProAcion();
			try {
				forward = action.execute(request, response);
			} catch (Exception e) {
				e.printStackTrace();
			}
		} 
			else if(command.equals("/QDetail.book")) {
			action = new QDetailProAcion();
			try {
				forward = action.execute(request, response);
			} catch (Exception e) {
				e.printStackTrace();
			} 
			
		} else if(command.equals("/QDeleteForm.book")){
			forward = new ActionForward();
			forward.setPath("./book/Q_delete.jsp");
		
		// 상품문의 글삭제
		} else if(command.equals("/QDeletePro.book")) {
			action = new QDeleteProAction();
			try {
				forward = action.execute(request, response);
			} catch (Exception e) {
				e.printStackTrace();
			}
			
		} else if(command.equals("/BookLike.book")) {
			action = new BookLikeProAction();
			try {
				forward = action.execute(request, response);
			} catch (Exception e) {
				e.printStackTrace();
			}
			
		// 장바구니 추가	
		} else if(command.equals("/CartAdd.book")) {
			action = new CartAddAction();
			try {
				forward = action.execute(request, response);
			} catch (Exception e) {
				e.printStackTrace();
			}
		// 장바구니 리스트	
		}else if(command.equals("/CartAdd2.book")) {
			action = new CartAddAction2();
			try {
				forward = action.execute(request, response);
			} catch (Exception e) {
				e.printStackTrace();
			}
		// 장바구니 리스트	
		} else if(command.equals("/CartList.book")) {
			action = new CartListAction();
			try {
				forward = action.execute(request, response);
			} catch (Exception e) {
				e.printStackTrace();
			}
		// 장바구니 수량 변경
		}else if(command.equals("/CartQtyChange.book")) {
			action = new CartQtyChangeAction();
			try {
				forward = action.execute(request, response);
			} catch (Exception e) {
				e.printStackTrace();
			}
			
		// 장바구니 삭제	
		}else if(command.equals("/CartRemove.book")) {
			action = new CartRemoveAction();
			try {
				forward = action.execute(request, response);
			} catch (Exception e) {
				e.printStackTrace();
			}
			
		} else if(command.equals("/BookBuy.book")) {
			action = new BookBuyAction();
			try {
				forward = action.execute(request, response);
			} catch (Exception e) {
				e.printStackTrace();
			}
			
		} else if(command.equals("/BookBuy2.book")) {
			action = new BookBuyAction2();
			try {
				forward = action.execute(request, response);
			} catch (Exception e) {
				e.printStackTrace();
			}
			
		} else if(command.equals("/BookBuyPro.book")) {
			action = new BookBuyProAction();
			try {
				forward = action.execute(request, response);
			} catch (Exception e) {
				e.printStackTrace();
			}
			
		} else if(command.equals("/BookBuyPro2.book")) {
			action = new BookBuyProAction2();
			try {
				forward = action.execute(request, response);
			} catch (Exception e) {
				e.printStackTrace();
			}
			
		} else if(command.equals("/BoardFileDown.bo")){
			forward = new ActionForward();
			forward.setPath("./board/file_down.jsp");

		}
		
		if(forward != null) {
			if(forward.isRedirect()) {
				response.sendRedirect(forward.getPath());
			}else {
				RequestDispatcher dispatcher = request.getRequestDispatcher(forward.getPath());
				
				dispatcher.forward(request, response);
			}
		}else {
			System.out.println("-- ActionForward object 값이 null입니 !!!! --");
		}
	}
	
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doProcess(request, response);
	}
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("123post");
		doProcess(request, response);
	}

}