package controller;

import java.io.IOException;
import java.util.Date;
import java.util.Properties;
import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;


@WebServlet("*.send")
public class CheckEmailController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	@Override
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String command = request.getServletPath();
		System.out.println(command);
		HttpSession session2 = request.getSession();
		
		
	       Properties props = System.getProperties();
	        props.put("mail.smtp.user", "forgiving3@naver.com"); // 서버 아이디만 쓰기
			props.put("mail.smtp.host", "smtp.naver.com"); // 구글 SMTP
			props.put("mail.smtp.port", "465");
			props.put("mail.smtp.starttls.enable", "true");
			props.put("mail.smtp.auth", "true");
			props.put("mail.smtp.socketFactory.port", "465");
			props.put("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
			props.put("mail.smtp.socketFactory.fallback", "false");
	           
	        Authenticator auth = new MyAuthentication();
	         
	        //session 생성 및  MimeMessage생성
	        Session session = Session.getDefaultInstance(props, auth);
	        MimeMessage msg = new MimeMessage(session);
	         
	        try{
	            //편지보낸시간
	            msg.setSentDate(new Date());
	             
	            InternetAddress from = new InternetAddress("forgiving3@naver.com") ;             

	            // 이메일 발신자
	            msg.setFrom(from);           
	             
	            // 이메일 수신자
	            String email = request.getParameter("email"); //사용자가 입력한 이메일 받아오기
	            InternetAddress to = new InternetAddress(email);
	            msg.setRecipient(Message.RecipientType.TO, to);
	             
	            // 이메일 제목
	            msg.setSubject("회원가입 인증 번호 입니다", "UTF-8");
	             
	            // 이메일 내용 

	            String code = request.getParameter("code_check"); //인증번호 값 받기
	            request.setAttribute("code", code);
	            session2.setAttribute("code",code);
	            
	            msg.setText(code, "UTF-8");
	             
	            // 이메일 헤더 
	            msg.setHeader("content-Type", "text/html");
	             
	            //메일보내기
	            javax.mail.Transport.send(msg);
	            System.out.println("보냄!");
	             
	        }catch (AddressException addr_e) {
	            addr_e.printStackTrace();
	        }catch (MessagingException msg_e) {
	            msg_e.printStackTrace();
	        }
	        System.out.println("어디까지왔니1");
	        RequestDispatcher rd = request.getRequestDispatcher("member/mailcheck.jsp");
	        rd.forward(request, response);
	        System.out.println("어디까지왔니2");
	}
	}

	class MyAuthentication extends Authenticator {
	      
	    PasswordAuthentication pa;
	    
	 
	    public MyAuthentication(){
	         
	        String id = "forgiving3@naver.com";       
	        String pw = "-dtsrw_";         
	 
	        // ID와 비밀번호를 입력한다.
	        pa = new PasswordAuthentication(id, pw);
	      
	    }
	 
	    // 시스템에서 사용하는 인증정보
	    public PasswordAuthentication getPasswordAuthentication() {
	        return pa;
	    }

}