package kr.or.ddit.servlet01;

import java.io.IOException;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 서블릿 
 *  ? 웹상에서 발생하는 요청을 받고 , 동적인 응답을 생성할 수 있는 자바 객체가 가져야 하는 조건에 대한 명세화 ==> HttpServlet 
 *  
 *  서블릿 스펙에서 주요 객체 
 *  1. HttpServlet : callback 메소드 (일정한 조건이 만들어 졌을 때 자동으로 실행되는 구문)
 *  	ex) doget, dopost....
 *  2. HttpServletRequest : http 규칙에 따라 패키징 된 요청에 대한 정보 (얘는 과거) 
 *  3. HttpServletResponse : http 규칙에 따라 패키징 될 응답에 대한 정보 (얘는 미래) 왜? 서버의 입장에서 요청은 받았고 응답을 할 예정이기 떄문
 *  4. HttpSession : 하나의 클라이언트 (에이전트)에 대한 정보를 가진 객체. 사람을 대상으로 하는것이 아니라 그 사람이 사용하고 있는 프로그램 수로 말한다. 
 *  5. ServletContext(기본적으로 싱글톤) : 서버와 컨텍스트에 대한 정보 (톰캣이 버전이 몇인지, 내가 사용하고 있는 context객체가 뭐지 확인하고 싶을때)
 *  6. ServletConfig : 하나의 서블릿에 대한 메타정보. [서블릿의 설정정보(config)]
 *     5/6번은 http가 안붙어있지? 그말은 요청과 응답하는것이 아니란 얘기 
 *   
 *   
 *   톰캣(WAS, Web Container, Servlet Container, JSP Container, Middle ware)의 역할 
 *   	container ? 내부에서 관리되는 객체의 생명주기 관리자. 
 *   	servlet container ?  내부에서 관리되는 servlet 객체의 생명주기 관리자. 
 *   		서블릿 컨테이너는 확장 CGI 방식에 따라 하나의 요청을 하나의 쓰레드로 처리함. 
 *   
 *   		일반적인 컨테이너의 특징
 *   			1) 싱글톤 구조로 서블릿을 관리함.  
 *   			2) 생명주기를 관리하는 과정에서 여러가지 상황에 대한 콜백 구조를 가짐. 
 *   
 *   생명주기 : init(초기화. 서블릿의 객체가 처음 생성되었을때), destory(필요없을때 삭제할때)
 *   요청 : service, doXXX
 *   
 *   
 */
@WebServlet("/desc") // CoC 패러다임. [Convention(관행,전통)OverConfiguration패러다임] 
public class DescriptionServlet extends HttpServlet{

	@Override
	public void init(ServletConfig config) throws ServletException {
		super.init(config);
		System.out.printf("%s 서블릿 초기화\n", this.getClass().getSimpleName());
		// 여기서 this는 DescriptionServlet의 객체. 누가 생성했어? 톰캣이. 
		// 서블릿에서 인스턴스(객체)를 한번만 생성하기 때문에 애는 한번만 출력된다. 
		// 하나의 싱글톤으로 돌려막기 하는중 
	}
	
	@Override // http request metohd 에 따른 분기자  
	protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		System.out.println("서비스 실행");
		//super.service(req, resp); // 여기서 doget실행 . 이게 없으면 출력안됨
		System.out.println("서비스 종료");
		// 확장 cgi구조...여러가지 쓰레드를 생성해서 실행한다. 
	}
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		System.out.println("doget 실행");
		resp.getWriter().printf(Thread.currentThread().getName());
	}
	
	@Override
	public void destroy() {
		System.out.printf("%s 서블릿 소멸\n", this.getClass().getSimpleName());
		
	
	}
}
