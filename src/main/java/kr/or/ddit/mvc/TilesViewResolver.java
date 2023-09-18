package kr.or.ddit.mvc;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
/**
 * logical view name 을 tiles의 definition name 으로 연결함.
 *
 */
public class TilesViewResolver implements ViewResolver {

	@Override
	public void resolveView(String logicalViewName, HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		String viewName = "/" + logicalViewName + ".tiles";
		req.getRequestDispatcher(viewName).forward(req, resp);
	}

}
