package kr.or.ddit.miles;

import java.io.IOException;
import java.io.InputStream;
import java.util.Optional;
import java.util.Properties;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


public class MilesDispatcherServlet extends HttpServlet{
	private Properties mileConfig;
	
	@Override
	public void init(ServletConfig config) throws ServletException {// init 쓰는 이유 ? 딱 한번만 생성되면 되기때문. 메모리 과부화 방지
		super.init(config);
		System.out.printf("%s 서블릿 초기화\n", this.getClass().getName());
	
		String configLocation = Optional.ofNullable(config.getInitParameter("configLocation"))
				.orElseThrow(()->new ServletException("configLocation 서블릿 파라미터 누락 "));
			// 여기까지 해석  getInitparameter로 configlocation이 있는지 확인. 확인했는데 있으면 거기에 있는 properties를 optional에 담아 
			// 없으면 예외발생 
		try(
			InputStream is = this.getClass().getResourceAsStream(configLocation);
				// 클래스 경로에서 해당위치의 리소스를 가져와서 is에 담기 내생각에는 그 properties에 잇는 경로 담아서 넣는것같음
		){			
			mileConfig = new Properties();// 이 안에 entry 2개잇음. 
			mileConfig.load(is);
		} catch (IOException e) {
			//예외 전환 하려면 throw가 필요 
			throw new ServletException(e);
		}
	}
	
	// service는 여기까지 왔을떄 get, post 상관없다~~
	@Override
	protected void service(HttpServletRequest req, HttpServletResponse resp) 
				throws ServletException, IOException {
		ServletConfig config = getServletConfig();// 서블릿의 설정정보를 담고 있는 인터페이스 이거 왜? 아래 template불러오려고
		
		config.getInitParameter("template");
		// 있냐없냐 api optional
		String template = Optional.ofNullable(config.getInitParameter("template"))
									.orElseThrow(()->new ServletException("template 이라는 서블릿 파라미터 누락"));
		
		mileConfig.forEach((k,v)->{
			req.setAttribute(k.toString(),v);
		});
		String servletPath = req.getServletPath();// sevletPat 요청한 곳의 주소. / formView...  
		int lastIdx = servletPath.lastIndexOf(".miles"); // 서블릿 경로에서 마지막 .miles 문자열의인덱스 찾기
		
		String logicalViewName = servletPath.substring(1, lastIdx);// 서블릿 경로에서 첫번째부터 .miles까지 저장하기 
	
		String prefix="/WEB-INF/views/";
		String suffix=".jsp";
		
		String contentPage = prefix + logicalViewName+ suffix;
		req.setAttribute("contentPage", contentPage);
		
		req.getRequestDispatcher(template).forward(req,resp);
		
	}
}
