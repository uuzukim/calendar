package kr.or.ddit.mvc;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 비동기 요청에 대한 응답처럼 전체 페이지 레이아웃이 필요 없는 경우, jsp하나로만 응답을 전송하기 위한 객체.
 * 
 */
public class InternalResouceViewResolver implements ViewResolver {

	private String prefix= "";
	private String suffix= "";
	
	public void setPrefix(String prefix) {
		this.prefix = prefix;
	}
	public void setSuffix(String suffix) {
		this.suffix = suffix;
	}
	
	@Override
	public void resolveView(String logicalViewName, HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		
		String viewName=prefix +logicalViewName+suffix;
		req.getRequestDispatcher(viewName).forward(req, resp);

		
	}

}
