
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
public class NoticeWriteProAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		ActionForward forward = null;
		// 리퀘스트 한글처리
		request.setCharacterEncoding("UTF-8");
		HttpSession session = request.getSession();
		// 관리자 체크
		if(!isAdmin(session)) {
			forward = deniedAccess(session);
			return forward;
		}
		// MultipartRequest 객체생성
		String saveFolder ="/boardFile";
//		File isDir = new File(saveFolder); // 파일 디렉토리가 없다면 만들어주기 위함
		
		
		ServletContext context = request.getServletContext(); // request 객체로부터 컨텍스트 객체 가져오기
		String realFolder = context.getRealPath(saveFolder); // 가상 위치로부터 실제 폴더 위치를 가져오기
		
		BoardBean bb = null;
		FileBean file = null; // 파일 정보를 저장할 변수 선언
		List<FileBean> fileList = new ArrayList<FileBean>(); // BoardBean 객체에 담을 fileList 객체 생성
		
		int maxSize=20*1024*1024; // 1024 비트(1키로바이트) * 1024 키로바이트(1메가바이트) * 20 => 20 메가바이트 
		MultipartRequest multi=new MultipartRequest(request,realFolder,maxSize,"utf-8",new DefaultFileRenamePolicy());
		Enumeration files = multi.getFileNames();
		
		while(files.hasMoreElements()) {
			String name = (String)files.nextElement();
			// 파일명 들고오기
			String originFilename = multi.getOriginalFileName(name); //  원본 파일명, 보여지는 파일명이다.
			String storedFileName = multi.getFilesystemName(name); // 저장되는 파일명, 중복처리를 거친 후 의 파일명이다.

			System.out.println("원본 파일명(보여지는 이름) : " + originFilename);
			System.out.println("저장된 파일명(중복처리) : " + storedFileName);
			
			if(originFilename != null) {
				String[] getFileType = originFilename.split("\\."); // 파일명 마지막의  확장자를 꺼내기 위하여 . 으로 문자열을 자름 
				// "." 은 정규식에서 무작위 문자라는 뜻이라서 . 으로 문자열을 자르기 위해서는 \\.  을 사용해야한다.
				String fileType = getFileType[getFileType.length - 1]; // 파일명 마지막이 .확장자로 끝나므로 끝 인덱스 값을 넣음
				file = new FileBean(originFilename, storedFileName, fileType);
				fileList.add(file);
			}
		}
		// DB작업을 위해 서비스 객체 생성
		BoardService boardService = new BoardService();
		// 카테고리 관련
		String k1 = "공지사항";
		// 글 번호 들고오기
		int boardNum = boardService.getMaxNum(k1) + 1;
		
		// 제목과 내용, 작성자
		String boardWriter = multi.getParameter("boardWriter");
		System.out.println("작성자는 " + boardWriter);
		String boardTitle = multi.getParameter("boardTitle");
		String boardContent = multi.getParameter("boardContent");
		
		// 작성일, 그룹번호, 글 레벨(답글 확인), 글 순서(답글 순서), 조회수, 상품 ID(상품 문의, 후기용)
		Timestamp boardRegTime = new Timestamp(System.currentTimeMillis());
		int boardReRef = boardNum;
		int boardReLev = 0;
		int boardReSeq = 0;
		int boardReadcount = 0;
		// BoardBean 에 파라미터 저장 및 생성
		bb = new BoardBean(boardNum, k1, boardWriter, boardTitle, boardContent, boardRegTime, boardReRef, boardReLev, boardReSeq, boardReadcount, fileList);
		
		// BoardBean 객체를 전달하여 서비스의 writeArticle() 메서드를 실행하여  DB에 글을 삽입하고, 성공 시 1을 반환받는다, 실패시 0을 반환
		int insertCount = boardService.writeArticle(bb);
		
		forward = new ActionForward();
		if(insertCount != 0) {
			// Notice 작성한거 상세보기
			forward.setPath("./NoticeDetail.adb?boardNum="+boardNum);
		} else {
			session.setAttribute("ErrorMSG", "게시글 작성에 실패하였습니다.");
			forward.setPath("failed.adb");
			forward.setRedirect(true);
		}
		
		return forward;
	}

}
