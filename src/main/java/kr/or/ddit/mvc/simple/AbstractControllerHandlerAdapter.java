package kr.or.ddit.mvc.simple;

import java.io.IOException;
import java.text.MessageFormat;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class AbstractControllerHandlerAdapter implements HandlerAdapter {

	@Override
	public String invokeHandler(AbstractController controller, HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		
		String method = req.getMethod();
		String logicalViewName = null;
		if (method.equalsIgnoreCase("get")) { // equalsIgnoreCase 대소문자 무시하겠다.
			log.info("{},{} 메소드 핸들러 실행",controller.getClass().getName(), method);//controller.getClass()어떤 컨트롤러
			logicalViewName = controller.getHandler(req, resp);
		} else if (method.equalsIgnoreCase("post")) {
			logicalViewName = controller.postHandler(req, resp);
		} else if  (method.equalsIgnoreCase("put")) {
			logicalViewName = controller.putHandler(req, resp);
		}	else if  (method.equalsIgnoreCase("delete")) {
			logicalViewName = controller.deleteHandler(req, resp);
		} else {
			resp.sendError(HttpServletResponse.SC_METHOD_NOT_ALLOWED,
					MessageFormat.format("{0} 메소드 요청은 지원되지 않습니다.", method));
		}
		return logicalViewName;
	}

}
