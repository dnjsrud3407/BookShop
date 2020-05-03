package member.book.action;

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
import admin.board.svc.BoardService;
import member.book.svc.ReviewWriteProService;
import vo.ActionForward;
import vo.BoardBean;
import vo.BookBean;
import vo.FileBean;

public class ReviewWriteProAcion implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		System.out.println("ReviewWriteProAcion");

		ActionForward forward = null;
		BoardBean boardBean = null;
		FileBean file = null; // 파일 정보를 저장할 변수 선언
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
//		String saveFolder = "/upload";
//		ServletContext context = request.getServletContext();
//		String realFolder = context.getRealPath(saveFolder);
//		int fileSize = 1024 * 1024 * 5;
//		MultipartRequest multi = new MultipartRequest(
//				request, 
//				realFolder, 
//				fileSize, 
//				"UTF-8",  
//				new DefaultFileRenamePolicy());
//
////		ReviewWriteProService reviewWriteProService = new ReviewWriteProService();
////		boolean isWriteSuccess = reviewWriteProService.registReview(bookBean);
//		
		ReviewWriteProService reviewWriteProService = new ReviewWriteProService();
		
		String k1 = multi.getParameter("k1");
		
		int boardNum = reviewWriteProService.getMaxNum(k1) + 1;
		
		String boardWriter = multi.getParameter("boardWriter");
		String boardTitle = multi.getParameter("boardTitle");
		String boardContent = multi.getParameter("boardContent");
		String score = multi.getParameter("score");
		
		Timestamp boardRegTime = new Timestamp(System.currentTimeMillis());
		
		int boardReRef = boardNum;
		int boardReLev = 0;
		int boardReSeq = 0;
		int boardReadcount = 0;
		int bookID = Integer.parseInt(multi.getParameter("bookID"));
		
		boardBean = new BoardBean(boardNum,k1,boardWriter,boardTitle,boardContent,boardRegTime,boardReRef,boardReLev,boardReSeq,boardReadcount,bookID,fileList,score);
		
		reviewWriteProService.registReview(boardBean);
		
		forward = new ActionForward();
		forward.setPath("Book.book?bookID=" + bookID); 
		forward.setRedirect(true); 
		
		return forward;
	}

}
