package admin.board.action;

import java.io.File;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.oreilly.servlet.MultipartRequest;
import com.oreilly.servlet.multipart.DefaultFileRenamePolicy;

import action.Action;
import admin.board.svc.BoardService;
import vo.ActionForward;
import vo.BoardBean;
import vo.FileBean;
import static access.Access.*;
public class FAQWriteFormAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		ActionForward forward = null;
		// 관리자 체크
		HttpSession session = request.getSession();
		if(!isAdmin(session)) {
			forward = deniedAccess(session);
			return forward;
		}
		BoardService boardService = new BoardService();
		
		String k1 = "FAQ";
		ArrayList<String> k2List = boardService.getk2List(k1);
		
		// FAQ 카테고리 목록
		request.setAttribute("k2List", k2List);
		
		forward = new ActionForward();
		
		forward.setPath("/admin/board/FAQWriteForm.jsp");
		
		return forward;
	}

}
