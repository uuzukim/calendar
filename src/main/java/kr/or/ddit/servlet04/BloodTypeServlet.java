package kr.or.ddit.servlet04;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/bloodType")
public class BloodTypeServlet extends HttpServlet{
	private Map<String,String[]> bloodType;

	@Override
	public void init(ServletConfig config) throws ServletException {
		super.init(config);
		
		bloodType = new HashMap<>();
		bloodType.put("BT01",new String[] {"A형", "blood/a"});
		bloodType.put("BT02",new String[] {"B형", "blood/b"});
		bloodType.put("BT03",new String[] {"AB형", "blood/ab"});
		bloodType.put("BT04",new String[] {"O형", "blood/o"});
		getServletContext().setAttribute("bloodType", bloodType);
	}
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		//req.setAttribute("bloodType", bloodType);
	
		//String viewName ="/WEB-INF/views/blood/formView.jsp"를 중복된 코드를 프레임워크 하기 위한작업..
		//논리적인 viewname이라고 부름
		String logicalViewName = "blood/formView";		
		String viewName = "/" + logicalViewName+ ".tiles"; // web.xml에서 urlpattern*miles 있는 곳에서 mileDispatcherServlet으로 보냄 그럼 그 클래스로 뿅! 
		req.getRequestDispatcher(viewName).forward(req,resp);
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// get갔다가 o형 값 입력하고 돌아온거야
		req.setCharacterEncoding("utf-8");
		
		String code = req.getParameter("blood");
		int status = 200;
		String logicalViewName = null;
		
		if(code == null || code.isEmpty()) {
			status = HttpServletResponse.SC_BAD_REQUEST;
		}else if(!bloodType.containsKey(code)){
			status = HttpServletResponse.SC_NOT_FOUND;
		}else {
			String[] bloodInfo = bloodType.get(code);		
			logicalViewName = bloodInfo[1];
	}
	
	if(status !=200) {
		resp.sendError(status);
	}else {

		String viewName = "/"+logicalViewName+ ".tiles";
		req.getRequestDispatcher(viewName).forward(req,resp);
	}
}
}
