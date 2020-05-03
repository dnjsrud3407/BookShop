package member.book.action;

import static db.JdbcUtil.*;

import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.oreilly.servlet.MultipartRequest;
import com.oreilly.servlet.multipart.DefaultFileRenamePolicy;

import action.Action;
import dao.BookDAO;
import member.book.svc.ReviewModifyProService;
import member.book.svc.ReviewWriteProService;
import vo.ActionForward;
import vo.BoardBean;
import vo.BookBean;
import vo.FileBean;

public class ReviewModifyProAcion implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		System.out.println("ReviewModifyProAcion");
		
		ActionForward forward = null;
		FileBean file = null; // 파일 정보를 저장할 변수 선언
		BoardBean boardBean = null;
		List<FileBean> fileList = new ArrayList<FileBean>(); // BoardBean 객체에 담을 fileList 객체 생성
		
		String saveFolder = "/boardFile";
		ServletContext context = request.getServletContext();
		String realFolder = context.getRealPath(saveFolder);
		int filesize = 1024 * 1024 * 10;
		
		MultipartRequest multi = new MultipartRequest(
				request, 
				realFolder,
				filesize,
				"UTF-8",
				new DefaultFileRenamePolicy());
		
		Enumeration files = multi.getFileNames();
		
		while(files.hasMoreElements()) {
			String name = (String)files.nextElement();
			
			String originFileName = multi.getOriginalFileName(name);
			String storedFileName = multi.getFilesystemName(name);
			
			System.out.println("원본 파일명(보여지는 이름) : " + originFileName);
			System.out.println("저장된 파일명(중복처리) : " + storedFileName);

			String[] getFileType = originFileName.split("\\."); // 파일명 마지막의  확장자를 꺼내기 위하여 . 으로 문자열을 자름	
			String fileType = getFileType[getFileType.length - 1]; // 파일명 마지막이 .확장자로 끝나므로 끝 인덱스 값을 넣음
			file = new FileBean(originFileName, storedFileName, fileType);
			fileList.add(file);
		}
		
		ReviewModifyProService reviewModifyProService = new ReviewModifyProService();
		
		String k1 = multi.getParameter("k1");
			
		int boardNum = Integer.parseInt(multi.getParameter("boardNum"));
		String boardWriter = multi.getParameter("boardWriter");
		String boardTitle = multi.getParameter("boardTitle");
		String boardContent = multi.getParameter("boardContent");
		String score = multi.getParameter("score");
		
		Timestamp boardRegTime = new Timestamp(System.currentTimeMillis());
		
		int boardReRef = boardNum;
		int boardReLev = 0;
		int boardReSeq = 0;
		int boardReadcount = 0;
				//Integer.parseInt(multi.getParameter("readCount"));
		int bookID = Integer.parseInt(multi.getParameter("bookID"));
		
		boardBean = new BoardBean(boardNum,k1,boardWriter,boardTitle,boardContent,boardRegTime,boardReRef,boardReLev,boardReSeq,boardReadcount,bookID,fileList,score);
		
		boolean ismodifySuccess = reviewModifyProService.modifyReview(boardBean);
//		String page = request.getParameter("page");

//		boolean isRightUser = false;
//		ReviewModifyProService bookModifyProService = new ReviewModifyProService();
//		bookModifyProService.isReviewWriter(num, request.getParameter("board_pass"));
		
		if(ismodifySuccess) {
			response.setContentType("text/html; charset=UTF-8");
			PrintWriter out = response.getWriter(); 
			out.println("<script>"); // 인터넷 밖으로 내보내는 
			out.println("alert('수정 성공!')"); // 경고 dialog출력
			out.println("self.close()");
			out.println("opener.location.reload()");
			out.println("</script>"); 
		} else {
			response.setContentType("text/html; charset=UTF-8");
			PrintWriter out = response.getWriter();
			out.println("<script>");
			out.println("alert('글 수정 실패!')"); // 경고 dialog출력
			out.println("history.go(-1)"); //back은 한칸만가능 go는 히스토리안에서 단계로 넘어갈수있음
			out.println("</script>");
		}
//			BookBean review = new BookBean();
			
//			article.setBoard_num(board_num);
//			article.setBoard_subject(request.getParameter("board_subject"));
//			article.setBoard_content(request.getParameter("board_content"));
			
//			boolean isModifySuccess = bookModifyProService.modifyReview(review);
//		
//			if(!isModifySuccess) {
//				response.setContentType("text/html; charset=UTF-8");
//				PrintWriter out = response.getWriter();
//				out.println("<script>");
//				out.println("alert('글 수정 실패!')"); // 경고 dialog출력
//				out.println("history.go(-1)"); //back은 한칸만가능 go는 히스토리안에서 단계로 넘어갈수있음
//				out.println("</script>");
//				
//			}else{
//				forward = new ActionForward();
//				forward.setPath("BookDetail.book?board_num=" + board_num + "&page=" + page);
//				forward.setPath("ReviewDetail.book");
//				forward.setRedirect(true);
//			}
//		}
//		forward = new ActionForward();
//		forward.setPath("Book.book?bookID=" + bookID);
//		forward.setRedirect(true);

		return null;
	}

}
