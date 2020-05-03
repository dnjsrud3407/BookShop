package board.action;

import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.oreilly.servlet.MultipartRequest;
import com.oreilly.servlet.multipart.DefaultFileRenamePolicy;

import action.Action;
import board.svc.QWriteProService;
import vo.ActionForward;
import vo.BoardBean;

public class QWriteProAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		System.out.println("QWriteProAcion");
		//-----------사용자 1:1 문의작성----------------------------------------
		ActionForward forward = null;
		SimpleDateFormat format = new SimpleDateFormat("yyyy-mm-dd");
		Date tempDate = null;
		
		BoardBean boardBean = new BoardBean();
		//카테고리 가져오기
		String kategorie=request.getParameter("kategorie");
		System.out.println("카테고리테스트 : "+kategorie);
		
		
		if (kategorie.equals("주문/결제")) {
			boardBean.setkID(109);
			System.out.println(boardBean.getkID());
		}else if(kategorie.equals("배송")) {
			boardBean.setkID(110);
			System.out.println(boardBean.getkID());
		}else if(kategorie.equals("취소/교환/반품")) {
			boardBean.setkID(111);
			System.out.println(boardBean.getkID());
		}else if(kategorie.equals("회원")) {
			boardBean.setkID(112);
			System.out.println(boardBean.getkID());
		}else if(kategorie.equals("기타")) {
			boardBean.setkID(113);
			System.out.println(boardBean.getkID());
		}else{
			System.out.println("아무것도 해당안됨");
		};
		
		
		System.out.println("테스트");
		
		//파일업로드
		String saveFolder = "/boardFile";
		ServletContext context = request.getServletContext();
		String realFolder = context.getRealPath(saveFolder);
		int fileSize = 1024 * 1024 * 5;
		MultipartRequest multi = new MultipartRequest(
				request, 
				realFolder, 
				fileSize, 
				"UTF-8",  
				new DefaultFileRenamePolicy());
		
		System.out.println("multipart 글쓴이 가져오기 : "+multi.getParameter("boardWriter"));
		System.out.println("multipart 제목 가져오기 : "+multi.getParameter("boarTitle"));
		System.out.println("multipart 내용 가져오기 : "+multi.getParameter("boardContent"));
		
		
		boardBean = new BoardBean(
				0,                 //boardNum
				boardBean.getkID(),
				multi.getParameter("boardWriter"),
				multi.getParameter("boardTitle"),
				multi.getParameter("boardContent"),
				0, //boardReRef
				0, //boardReLev
				0, //boardReSeq
				0, //boardReadcount
				0, //bookID는 일딴 임시로 0 
				multi.getOriginalFileName((String)multi.getFileNames().nextElement()), //서버 업로드시 선택한 원본이름
				multi.getFilesystemName((String)multi.getFileNames().nextElement())//업로드 된 후 이름
						);
		
		
		System.out.println("실제파일이름 : "+boardBean.getOriginFileName());
		System.out.println("업로드된 이름 : "+boardBean.getStoredFileName());
//		fileNum, originFileName, storedFileName, fileType, board_boardNum, board_kID, board_boardWriter
		

		QWriteProService qWriteProService = new QWriteProService();
		boolean isWriteSuccess = qWriteProService.registQuestions(kategorie,boardBean);
		
		if (isWriteSuccess) {
			response.setContentType("text/html; charset=UTF-8");
			PrintWriter out = response.getWriter(); // HTML 태그 출력을 위한 Writer 객체 가져오기
			out.println("<script>"); 
			out.println("alert('문의가 등록 되었습니다')"); // alert dialog 출력
			out.println("</script>");
			
			forward = new ActionForward();
			forward.setPath("QList.bo"); 
			forward.setRedirect(true); 
		}else {
			response.setContentType("text/html; charset=UTF-8");
			PrintWriter out = response.getWriter(); // HTML 태그 출력을 위한 Writer 객체 가져오기
			out.println("<script>"); 
			out.println("alert('등록실패')"); // alert dialog 출력
			out.println("history.back()");
			out.println("</script>");
		}
		
		
		
		return forward;
	}

}

