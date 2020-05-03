package controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import action.Action;
import admin.board.action.CouponDeleteAction;
import admin.board.action.CouponInsertAction;
import admin.board.action.CouponListAction;
import admin.board.action.CouponModifyAction;
import admin.board.action.EventDeleteAction;
import admin.board.action.EventDetailAction;
import admin.board.action.EventListAction;
import admin.board.action.EventModifyFormAction;
import admin.board.action.EventModifyProAction;
import admin.board.action.EventWriteAction;
import admin.board.action.EventWriteProAction;
import admin.board.action.FAQDeleteProAction;
import admin.board.action.FAQModifyFormAction;
import admin.board.action.FAQModifyProAction;
import admin.board.action.FAQWriteFormAction;
import admin.board.action.FAQWriteProAction;
import admin.board.action.NoticeDeleteProAction;
import admin.board.action.NoticeDetailAction;
import admin.board.action.NoticeModifyProAction;
import admin.board.action.NoticeWriteProAction;
import admin.board.action.QDeleteProAction;
import admin.board.action.QDetailAction;
import admin.board.action.QListAction;
import admin.board.action.QListNAAction;
import admin.board.action.QModifyProAction;
import admin.board.action.FAQListAction;
import admin.board.action.NoticeListAction;
import admin.board.action.NoticeModifyFormAction;
//import admin.board.action.MemberQListAction;
import admin.board.action.QWriteProAction;
//import admin.board.action.MemberQdetailAction;
import vo.ActionForward;

@WebServlet("*.adb")
public class AdminBoardController extends HttpServlet {
	
    protected void doProcess(HttpServletRequest request, HttpServletResponse response) throws ServletException,IOException {
		
		request.setCharacterEncoding("utf-8");
		
		String command = request.getServletPath();
		System.out.println(command);
		
		Action action = null;
		ActionForward forward = null;
		
		// 서블릿 주소에 따라 각각 다른 작업을 수행
		if (command.equals("/QListNA.adb")) {//	1:1 문의 답변 작성 폼
			action = new QListNAAction();
			try {
				forward = action.execute(request, response);
			} catch (Exception e) {
				e.printStackTrace();
			}
			
		} else if(command.equals("/QList.adb")) {//1:1 문의 목록 보기 (미답변 글 위주)
			
			action = new QListAction();
			try {
				forward = action.execute(request, response);
			} catch (Exception e) {
				e.printStackTrace();
			}
		} else if(command.equals("/QDetail.adb")) {//1:1 문의 내용 보기
			
			action = new QDetailAction();
			
			try {
				forward = action.execute(request, response);
			} catch (Exception e) {
				e.printStackTrace();
			}
            
        }  else if(command.equals("/QWrite.adb")) {//1:1 문의 답변 작성
			
			action = new QWriteProAction();
			try {
				forward = action.execute(request, response);
			} catch (Exception e) {
				e.printStackTrace();
			}
            
        } else if(command.equals("/QModify.adb")) {//1:1 문의 답변 수정
			
			action = new QModifyProAction();
			try {
				forward = action.execute(request, response);
			} catch (Exception e) {
				e.printStackTrace();
			}
            
        } else if(command.equals("/QDelete.adb")) {//1:1 문의 답변 삭제
			
			action = new QDeleteProAction();
			try {
				forward = action.execute(request, response);
			} catch (Exception e) {
				e.printStackTrace();
			}
            
            //
            // FAQ 관련
            //
        } else if(command.equals("/FAQ.adb")) { // --- FAQ
			// FAQ 목록 FAQListAction()
			action = new FAQListAction();
			try {
				forward = action.execute(request, response);
			} catch (Exception e) {
				e.printStackTrace();
			}
		} else if(command.equals("/FAQWrite.adb")) {
            // --- FAQ 작성 폼 
            action = new FAQWriteFormAction();
            
            try {
                forward = action.execute(request, response);
            } catch (Exception e) {
                e.printStackTrace();
            }
            
            
            
        } else if(command.equals("/FAQWritePro.adb")) {
            // --- FAQ 작성 작업 FAQWriteProAction()
            action = new FAQWriteProAction();
            try {
                forward = action.execute(request, response);
            } catch (Exception e) {
                e.printStackTrace();
            }
        } else if(command.equals("/FAQModify.adb")) {
            // --- FAQ 수정 폼 (관리자가 작성한 내용 불러옴)
            action = new FAQModifyFormAction();
            try {
                forward = action.execute(request, response);
            } catch (Exception e) {
                e.printStackTrace();
            }
        } else if(command.equals("/FAQModifyPro.adb")) {
            // --- FAQ 수정 작업 
            action = new FAQModifyProAction();
            try {
                forward = action.execute(request, response);
            } catch (Exception e) {
                e.printStackTrace();
            }
        } else if(command.equals("/FAQDelete.adb")) {
            // --- FAQ 삭제 작업 
            action = new FAQDeleteProAction();
            try {
                forward = action.execute(request, response);
            } catch (Exception e) {
                e.printStackTrace();
            }
            
            //
            // 공지 관련
            //
        } else if(command.equals("/Notice.adb")) { // --- Notice
            // Notice 목록 NoticeListAction()
            action = new NoticeListAction();
            try {
                forward = action.execute(request, response);
            } catch (Exception e) {
                e.printStackTrace();
            }
        } else if(command.equals("/NoticeWrite.adb")) {
            // --- Notice 작성 폼 
            forward = new ActionForward();
            forward.setPath("./admin/board/NoticeWriteForm.jsp");
        } else if(command.equals("/NoticeWritePro.adb")) {
            // --- Notice 작성 작업 NoticeWriteProAction()
            action = new NoticeWriteProAction();
            try {
                forward = action.execute(request, response);
            } catch (Exception e) {
                e.printStackTrace();
            }
        } else if(command.equals("/NoticeDetail.adb")) {
            // --- Notice 상세보기
            action = new NoticeDetailAction();
            try {
                forward = action.execute(request, response);
            } catch (Exception e) {
                e.printStackTrace();
            }
        } else if(command.equals("/NoticeModify.adb")) {
            // --- Notice 수정 폼 (관리자가 작성한 내용 불러옴)
            action = new NoticeModifyFormAction();
            try {
                forward = action.execute(request, response);
            } catch (Exception e) {
                e.printStackTrace();
            }
        } else if(command.equals("/NoticeModifyPro.adb")) {
            // --- Notice 수정 작업 
            action = new NoticeModifyProAction();
            try {
                forward = action.execute(request, response);
            } catch (Exception e) {
                e.printStackTrace();
            }
        } else if(command.equals("/NoticeDelete.adb")) {
            // --- Notice 삭제 작업 
            action = new NoticeDeleteProAction();
            try {
                forward = action.execute(request, response);
            } catch (Exception e) {
                e.printStackTrace();
            }
            //
            //
            //
            // 공지사항
            
            
            
        } else if(command.equals("/Event.adb")) {
            // --- Notice 삭제 작업 
            action = new EventListAction();
            try {
                forward = action.execute(request, response);
            } catch (Exception e) {
                e.printStackTrace();
            }
        } else if(command.equals("/EventWrite.adb")) {
        	action = new EventWriteAction();
        	try {
                forward = action.execute(request, response);
            } catch (Exception e) {
                e.printStackTrace();
            }
        } else if(command.equals("/EventWritePro.adb")) {
            // --- Notice 삭제 작업 
            action = new EventWriteProAction();
            try {
                forward = action.execute(request, response);
            } catch (Exception e) {
                e.printStackTrace();
            }
        } else if(command.equals("/EventDetail.adb")) {
            // --- 이벤트 상세보기
            action = new EventDetailAction();
            try {
                forward = action.execute(request, response);
            } catch (Exception e) {
                e.printStackTrace();
            }
        } else if(command.equals("/EventModify.adb")) {
            // --- 이벤트 수정 폼 (관리자가 작성한 내용 불러옴)
            action = new EventModifyFormAction();
            try {
                forward = action.execute(request, response);
            } catch (Exception e) {
                e.printStackTrace();
            }
        } else if(command.equals("/EventModifyPro.adb")) {
            // --- Notice 수정 작업 
            action = new EventModifyProAction();
            try {
                forward = action.execute(request, response);
            } catch (Exception e) {
                e.printStackTrace();
            }
        } else if(command.equals("/EventDelete.adb")) {
            // --- Notice 삭제 작업 
            action = new EventDeleteAction();
            try {
                forward = action.execute(request, response);
            } catch (Exception e) {
                e.printStackTrace();
            }
        } else if(command.contentEquals("/failed.adb")) {
        	forward = new ActionForward();
        	forward.setPath("/admin/board/failedMSG.jsp");
        	
        	
        	// 쿠폰 관리
        } else if(command.contentEquals("/Coupon.adb")) {
        	action = new CouponListAction();
        	try {
        		forward = action.execute(request, response);
        	} catch (Exception e) {
        		e.printStackTrace();
			}
        	
        	// 쿠폰 생성
        } else if(command.contentEquals("/CounponInsert.adb")) {
        	action = new CouponInsertAction();
        	try {
        		forward = action.execute(request, response);
        	} catch (Exception e) {
        		e.printStackTrace();
			}
        	
        	// 쿠폰 수정
        } else if(command.contentEquals("/CounponModify.adb")) {
        	action = new CouponModifyAction();
        	try {
        		forward = action.execute(request, response);
        	} catch (Exception e) {
        		e.printStackTrace();
			}
        	
        	// 쿠폰 삭제
        } else if(command.contentEquals("/CouponDelete.adb")) {
        	action = new CouponDeleteAction();
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
