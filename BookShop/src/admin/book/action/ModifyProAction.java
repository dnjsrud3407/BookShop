package admin.book.action;

import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.oreilly.servlet.MultipartRequest;
import com.oreilly.servlet.multipart.DefaultFileRenamePolicy;

import action.Action;
import admin.book.svc.ModifyProService;
import admin.book.svc.WriteProService;
import vo.ActionForward;
import vo.BookBean;

public class ModifyProAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		ActionForward forward = null;
		
		int bookID = Integer.parseInt(request.getParameter("bookID"));
		String page = request.getParameter("page");
		
		// 파일 업로드 설정//
        String saveFolder = "/upload";
        ServletContext context = request.getServletContext(); 
        String realFolder = context.getRealPath(saveFolder);
        int fileSize = 1024 * 1024 * 10;
        
        MultipartRequest multi = new MultipartRequest(
                request,
                realFolder,      
                fileSize, 
                "UTF-8", 
                new DefaultFileRenamePolicy()  
                );
        
        // 전의 이미지 값 파라미터로 가져오기
        String beforeImage = multi.getParameter("beforeImage");
        String beforeOriginImage = multi.getParameter("beforeOriginImage");
        
        // 서버에 저장되는 상품 이미지 이름
        String bookImage = multi.getFilesystemName((String)multi.getFileNames().nextElement());
        // 상품 이미지
        String bookOriginImage = multi.getOriginalFileName((String)multi.getFileNames().nextElement());
        
        // ==== 이미지 변경 안 된 경우 - 전의 값 
        if(bookOriginImage == null) {
            bookImage = beforeImage;
            bookOriginImage = beforeOriginImage;
        }
        
        // ===== 책 카테고리 찾기
        WriteProService writeProService = new WriteProService();
        String BK1 = multi.getParameter("BK1Category");
        String BK2 = multi.getParameter("BK2Category");
        String BK3 = multi.getParameter("BK3Category");
        int BKID = writeProService.getBKID(BK1, BK2, BK3);
        
        // ===== 날짜 값 Date로 변환하기
        String publishedDate = multi.getParameter("bookPublishedDate"); // 날짜 값
        Date bookPublishedDate = null;
        // 날짜 입력했을 경우 변환
        if(!publishedDate.equals("") && !publishedDate.trim().equals("null")) {		
        	SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");   // 날짜 형식
        	bookPublishedDate = format.parse(publishedDate);
        }
        
        // ==== 공개 / 비공개 여부 설정
        boolean bookisView = false;
        String bookisViewStr = multi.getParameter("bookisView");
        if (bookisViewStr.equals("true")) { // 공개로 설정 시
            bookisView = true;
        }
        
        float saveRatio = (float) 0.05;
        String stringSaveRatio = request.getParameter("saveRatio");
        // 적립율 입력했을 때 변환
        if (stringSaveRatio != null) {
        	saveRatio = Float.parseFloat(stringSaveRatio);
        }
        
        // form 태그에서 책 정보 가져오기
        BookBean book = new BookBean(
                bookID,
                multi.getParameter("bookTitle"), 
                bookOriginImage, 
                bookImage, 
                multi.getParameter("bookPublisher"), 
                bookPublishedDate,
                Integer.parseInt(multi.getParameter("bookPrice")), 
                Integer.parseInt(multi.getParameter("bookEA")), 
                multi.getParameter("bookIntroduce"), 
                bookisView,
                saveRatio,
                BKID, BK1, BK2, BK3
                );
        
        // 책 수정하기
		ModifyProService modifyProService = new ModifyProService();
		boolean isModifySuccess = modifyProService.modifyArticle(book);
		
		if(!isModifySuccess) {
            response.setContentType("text/html; charset=UTF-8");
            PrintWriter out = response.getWriter();
            out.println("<script>");
            out.println("alert('글 수정 실패!')");
            out.println("history.back()");
            out.println("</script>");
        } else {
            forward = new ActionForward();
            forward.setPath("Detail.abook?bookID=" + book.getBookID() + "&page=" + page);
//            forward.setPath("Detail.abook?bookID=" + book.getBookID() + "&BKID=" + BKID);
        }
		
		return forward;
	}

}
