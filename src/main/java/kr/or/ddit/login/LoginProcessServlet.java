package kr.or.ddit.login;

import java.io.IOException;
import java.util.Optional;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.lang3.StringUtils;

import kr.or.ddit.login.service.AuthenticateService;
import kr.or.ddit.login.service.AuthenticateServiceImpl;
import kr.or.ddit.mvc.simple.AbstractController;
import kr.or.ddit.vo.MemberVO;


@WebServlet("/login/loginProcess")
public class LoginProcessServlet extends HttpServlet{
	  private AuthenticateService service = new AuthenticateServiceImpl();
	
//	private boolean authenticate(String memId, String memPass) {
//		return memId.equals(memPass);
//	}
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		//*1. 요청 접수(파라미터나 헤더와 관련된 데이터 확보->검증)
		req.setCharacterEncoding("utf-8");
		String memId = req.getParameter("memId");
		String memPass = req.getParameter("memPass");
		boolean valid =  StringUtils.isNotBlank(memId)&&
						StringUtils.isNotBlank(memPass);
		
		boolean saveFlag = Optional.ofNullable(req.getParameter("idSave"))
		/*saveflag는 지금 있거나 없거나겠찌	*/ .map(c->true) // 이건 지금 문자열인 형태를 불린형태로 바꾸는거야.
									   .orElse(false);
		
		//*3. model 을 공유 ->scope's setAttribute(name, value)

		//*4. view layer 선택 : logical view Name		
		String viewName="";
		HttpSession session = req.getSession();
		
		
		if(!valid) {
			//resp.sendError(400, "아이디나 비번 누락.");
			//return;
			session.setAttribute("message", "아이디나 비번 누락"); // 왜 req가 더 좁은 범위인데 굳이 session을 쓸까? 이유는 아래 redirect때문. redirect로 보내면 req가 사라지니까 session으로.. 
			viewName="redirect:/login/loginForm.jsp";
		}else {
			
			
			//*2. Model information 생성
			//*   #data vs information vs content(view layer, MIME type)
			
			  MemberVO inputData = new MemberVO();
		         inputData.setMemId(memId);
		         inputData.setMemPass(memPass);

			try {
				MemberVO authMember = service.authenticate(inputData);
				session.setAttribute("authMember", authMember);
				// redirect ?? 현재 요청에 대한 정보를 제거.
				viewName="redirect:/";
				//Cookie : client side 저장 데이터 
				Cookie idCookie = new Cookie("idCookie", memId);
				idCookie.setPath(req.getContextPath());
				if(saveFlag) {
					// create
					idCookie.setMaxAge(60*60*24*7);// setMaxAge초단위 
				}else { //저장안할거야~ 저장된 아이디가 있으면 지워 ㅡㅡ^
					// remove
					idCookie.setMaxAge(0);
				}
				resp.addCookie(idCookie);
			}catch(AuthenticateException e) {
				session.setAttribute("message",e.getMessage());
				viewName="redirect:/login/loginForm.jsp";
	//			req.getRequestDispatcher(viewName).forward(req,resp);
			}
		
		}

//		*5. view 로 이동 : forward[redirect]
		if(viewName.startsWith("redirect:")) {
			viewName=viewName.substring("redirect:".length());
			resp.sendRedirect(req.getContextPath()+viewName); // contextpath 를 쓰는이유는 redirect라서 
		}else {
			req.getRequestDispatcher(viewName).forward(req,resp);
		}
	}
}
