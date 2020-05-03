package controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import action.Action;
import admin.board.action.EventDetailAction;
import admin.board.action.EventListAction;
import board.action.EventUserDetailAction;
import board.action.EventUserListAction;
import board.action.FAQDeleteProAction;
import board.action.FAQDetailAction;
import board.action.FAQListAction;
import board.action.FAQModifyFormAction;
import board.action.FAQModifyProAction;
import board.action.FAQWriteProAction;
import board.action.NoticeDeleteProAction;
import board.action.NoticeDetailAction;
import board.action.NoticeListAction;
import board.action.NoticeModifyFormAction;
import board.action.NoticeModifyProAction;
import board.action.NoticeWriteProAction;
import board.action.MemberQDeleteProAction;
import board.action.MemberQModifyFormAction;
import board.action.MemberQModifyProAction;
import board.action.MemberQWriteFormProAction;
import board.action.MemberQListAction;
import board.action.QDeleteProAction;
import board.action.QDetailAction;
import board.action.QListAction;
import board.action.QModifyFormAction;
import board.action.QModifyProAction;
import board.action.QWriteFormAction;
import board.action.QWriteProAction;
import board.action.MemberQdetailAction;
import vo.ActionForward;

@WebServlet("*.bo")
public class BoardController extends HttpServlet {
	
    protected void doProcess(HttpServletRequest request, HttpServletResponse response) throws ServletException,IOException {
		
		request.setCharacterEncoding("utf-8");
		
		String command = request.getServletPath();
		System.out.println(command);
		
		Action action = null;
		ActionForward forward = null;
		
		// 서블릿 주소에 따라 각각 다른 작업을 수행
		if (command.equals("/QWriteForm.bo")) {//
			forward = new ActionForward();
			forward.setPath("//board/QWriteForm.jsp");
		} else if(command.equals("/WriteFormPro.bo")) {//
			action = new MemberQWriteFormProAction();
			try {
				forward = action.execute(request, response);
			} catch (Exception e) {
				e.printStackTrace();
			}
		} else if(command.equals("/QModify.bo")) {//1:1문의 수정폼
			
			action = new MemberQModifyFormAction();
			try {
				forward = action.execute(request, response);
			} catch (Exception e) {
				e.printStackTrace();
			}
		} 
		 else if(command.equals("/QWriteList.bo")) {//1:1 문의내역보기
			
			action = new MemberQListAction();
			try {
				forward = action.execute(request, response);
			} catch (Exception e) {
				e.printStackTrace();
			}
		} else if(command.equals("/QList.bo")) { // ---------------------- 관리자 Area --- 1:1 
			// 1:1 목록 QListAction()
			action = new QListAction();
			try {
				forward = action.execute(request, response);
			} catch (Exception e) {
				e.printStackTrace();
			}
		} else if(command.equals("/QWriteForm.bo")) {
            // --- 1:1 답변 작성 폼 (사용자가 작성한 내용을 불러와야 함)
			
//		    forward = new ActionForward();
//            forward.setPath("./board/QWriteForm.jsp");
            action = new QWriteFormAction();
            try {
                forward = action.execute(request, response);
            } catch (Exception e) {
                e.printStackTrace();
            }
        } else if(command.equals("/QWritePro.bo")) {
            // --- 1:1 작성 작업 QWriteProAction()
            action = new QWriteProAction();
            try {
                forward = action.execute(request, response);
            } catch (Exception e) {
                e.printStackTrace();
            }
        } else if(command.equals("/QDetail.bo")) {
			// 1:1 답변 상세보기
			action = new QDetailAction();
			try {
				forward = action.execute(request, response);
			} catch (Exception e) {
				e.printStackTrace();
			}
		} else if(command.equals("/QModifyForm.bo")) {
			// 1:1 글 수정하기 폼 (사용자가 작성한 내용 & 관리자가 작성한 내용 불러옴)
			action = new QModifyFormAction();
			try {
				forward = action.execute(request, response);
			} catch (Exception e) {
				e.printStackTrace();
			}
		} else if(command.equals("/QModifyPro.bo")) {
			// 1:1 글 수정 작업 
			action = new QModifyProAction();
			try {
				forward = action.execute(request, response);
			} catch (Exception e) {
				e.printStackTrace();
			}
		} else if(command.equals("/QDeleteForm.bo")) {
			// 1:1 글 삭제 폼
		    forward = new ActionForward();
            forward.setPath("./board/QDeleteForm.jsp");
		} else if(command.equals("/QDeletePro.bo")) {
            // --- 1:1 글 삭제 작업 
            action = new QDeleteProAction();
            try {
                forward = action.execute(request, response);
            } catch (Exception e) {
                e.printStackTrace();
            }
        } else if(command.equals("/FAQList.bo")) { // --- FAQ
			// FAQ 목록 FAQListAction()
			action = new FAQListAction();
			try {
				forward = action.execute(request, response);
			} catch (Exception e) {
				e.printStackTrace();
			}
		} else if(command.equals("/FAQWriteForm.bo")) {
            // --- FAQ 작성 폼 
            forward = new ActionForward();
            forward.setPath("./board/FAQWriteForm.jsp");
        } else if(command.equals("/FAQWritePro.bo")) {
            // --- FAQ 작성 작업 FAQWriteProAction()
            action = new FAQWriteProAction();
            try {
                forward = action.execute(request, response);
            } catch (Exception e) {
                e.printStackTrace();
            }
        } else if(command.equals("/FAQDetail.bo")) {
            // --- FAQ 상세보기
            action = new FAQDetailAction();
            try {
                forward = action.execute(request, response);
            } catch (Exception e) {
                e.printStackTrace();
            }
        } else if(command.equals("/FAQModifyForm.bo")) {
            // --- FAQ 수정 폼 (관리자가 작성한 내용 불러옴)
            action = new FAQModifyFormAction();
            try {
                forward = action.execute(request, response);
            } catch (Exception e) {
                e.printStackTrace();
            }
        } else if(command.equals("/FAQModifyPro.bo")) {
            // --- FAQ 수정 작업 
            action = new FAQModifyProAction();
            try {
                forward = action.execute(request, response);
            } catch (Exception e) {
                e.printStackTrace();
            }
        } else if(command.equals("/FAQDeleteForm.bo")) {
            // --- FAQ 삭제 폼
            forward = new ActionForward();
            forward.setPath("./board/FAQDeleteForm.jsp");
        } else if(command.equals("/FAQDeletePro.bo")) {
            // --- FAQ 삭제 작업 
            action = new FAQDeleteProAction();
            try {
                forward = action.execute(request, response);
            } catch (Exception e) {
                e.printStackTrace();
            }
        } else if(command.equals("/NoticeList.bo")) { // --- Notice
            // Notice 목록 NoticeListAction()
            action = new NoticeListAction();
            try {
                forward = action.execute(request, response);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }else if(command.equals("/EventList.bo")) { //----------Event List
            action = new EventListAction();
            try {
                forward = action.execute(request, response);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }else if(command.equals("/EventUserList.bo")) { //----------Event List
            action = new EventUserListAction();
            try {
                forward = action.execute(request, response);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }else if(command.equals("/EventUserDetail.bo")) { //----------Event List
            action = new EventUserDetailAction();
            try {
                forward = action.execute(request, response);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }else if(command.equals("/EventDetail.bo")) { //------------Event Detail
            action = new EventDetailAction();
            try {
                forward = action.execute(request, response);
            } catch (Exception e) {
                e.printStackTrace();
            }
        } else if(command.equals("/NoticeWriteForm.bo")) {
            // --- Notice 작성 폼 
            forward = new ActionForward();
            forward.setPath("./board/NoticeWriteForm.jsp");
        } else if(command.equals("/NoticeWritePro.bo")) {
            // --- Notice 작성 작업 NoticeWriteProAction()
            action = new NoticeWriteProAction();
            try {
                forward = action.execute(request, response);
            } catch (Exception e) {
                e.printStackTrace();
            }
        } else if(command.equals("/NoticeDetail.bo")) {
            // --- Notice 상세보기
            action = new NoticeDetailAction();
            try {
                forward = action.execute(request, response);
            } catch (Exception e) {
                e.printStackTrace();
            }
        } else if(command.equals("/NoticeModifyForm.bo")) {
            // --- Notice 수정 폼 (관리자가 작성한 내용 불러옴)
            action = new NoticeModifyFormAction();
            try {
                forward = action.execute(request, response);
            } catch (Exception e) {
                e.printStackTrace();
            }
        } else if(command.equals("/NoticeModifyPro.bo")) {
            // --- Notice 수정 작업 
            action = new NoticeModifyProAction();
            try {
                forward = action.execute(request, response);
            } catch (Exception e) {
                e.printStackTrace();
            }
        } else if(command.equals("/NoticeDeleteForm.bo")) {
            // --- Notice 삭제 폼
            forward = new ActionForward();
            forward.setPath("./board/NoticeDeleteForm.jsp");
        } else if(command.equals("/NoticeDeletePro.bo")) {
            // --- Notice 삭제 작업 
            action = new NoticeDeleteProAction();
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
