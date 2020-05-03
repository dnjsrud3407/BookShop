package board.action;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.oreilly.servlet.MultipartRequest;
import com.oreilly.servlet.multipart.DefaultFileRenamePolicy;

import action.Action;
import board.svc.QModifyProService;
import vo.ActionForward;
import vo.BoardBean;

public class QModifyProAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		System.out.println("QModifyProAction 수정하러가기");
		ActionForward forward = null;
		BoardBean boardBean = new BoardBean();
		
		int kID=Integer.parseInt(request.getParameter("kID"));
		System.out.println(kID);
		int result=0;
				QModifyProService q_ModifyProService = new QModifyProService();
		
		String saveFolder = "/upload";
		ServletContext context = request.getServletContext();
		String realFolder = context.getRealPath(saveFolder);
		int fileSize = 1024 * 1024 * 5;
		MultipartRequest multi = new MultipartRequest(
				request, 
				realFolder, 
				fileSize, 
				"UTF-8",  
				new DefaultFileRenamePolicy());
		
		int boardNum=Integer.parseInt(multi.getParameter("boardNum"));
		boardBean.setBoardWriter(multi.getParameter("boardWriter"));
		boardBean.setBoardNum(Integer.parseInt(multi.getParameter("boardNum")));
		boardBean.setkID(Integer.parseInt(multi.getParameter("KID")));
		boardBean.setBoardTitle(multi.getParameter("boardTitle"));
		boardBean.setBoardContent(multi.getParameter("boardContent"));
		
		String orginal =multi.getOriginalFileName((String)multi.getFileNames().nextElement());
		String file = multi.getFilesystemName((String)multi.getFileNames().nextElement());		
		String old_file=multi.getParameter("old_file");
		
		if (orginal !=null) {
			boardBean.setOriginFileName(multi.getOriginalFileName((String)multi.getFileNames().nextElement())); //원본
			boardBean.setStoredFileName(multi.getFilesystemName((String)multi.getFileNames().nextElement()));//업로드시
		}else {
			boardBean.setOriginFileName(old_file);
			boardBean.setStoredFileName(old_file);
		}
		
		
		System.out.println("실제파일이름 : "+boardBean.getOriginFileName());
		System.out.println("업로드된 이름 : "+boardBean.getStoredFileName());
		
		result=q_ModifyProService.oneOnOneModify(boardBean);
		
		
		
		
		forward = new ActionForward();
		forward.setPath("QList.bo");
		forward.setRedirect(true);
		
		return forward;
	}

}
