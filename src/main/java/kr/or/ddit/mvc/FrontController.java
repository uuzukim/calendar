package kr.or.ddit.mvc;

import java.io.IOException;
import java.text.MessageFormat;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import kr.or.ddit.mvc.simple.AbstractController;
import kr.or.ddit.mvc.simple.AbstractControllerHandlerAdapter;
import kr.or.ddit.mvc.simple.AbstractControllerHandlerMapping;
import kr.or.ddit.mvc.simple.HandlerAdapter;
import kr.or.ddit.mvc.simple.HandlerMapping;

public class FrontController extends HttpServlet{
	
	private HandlerMapping handlerMapping;
	private HandlerAdapter handlerAdapter;
	private ViewResolver viewResolver;
	
	@Override
	public void init(ServletConfig config) throws ServletException {
		super.init(config);
		String location =config.getInitParameter("configLocation");
		handlerMapping = new AbstractControllerHandlerMapping(location);
		handlerAdapter = new AbstractControllerHandlerAdapter();
		viewResolver = new ViewResolverComposite();
	
	}
	
	@Override
	protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.setCharacterEncoding("utf-8");

		String logicalViewName = null;

		// 컨트롤러 이제 찾아야지? handlermapping을 이용해서 
		AbstractController controller = handlerMapping.findCommandHandler(req);
		
		if(controller == null ) {
			resp.sendError(404, MessageFormat.format("{0} 서비스는 제공되지 않습니다.",req.getRequestURI()));
			return;
		}
		
		logicalViewName = handlerAdapter.invokeHandler(controller, req, resp);

		if(logicalViewName==null ) { // 이건 개발자 잘못.
			if(!resp.isCommitted())
				resp.sendError(500,"logical view name 이 결정되지 않았음.");
			return;
		}
		
		
		if (logicalViewName.startsWith("redirect:")) {
			logicalViewName = logicalViewName.substring("redirect:".length());
			resp.sendRedirect(req.getContextPath() + logicalViewName); // contextpath 붙는이유? redirect라서!
		} else {
			
			viewResolver.resolveView(logicalViewName, req, resp);
			
		}
	}
}
