package kr.or.ddit.mvc;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Command Handler 에서 결정된 logical view name 으로 실제 view로 이동하기 위한 전략 객체. 
 * 
 */
public interface ViewResolver {
	public void resolveView(String viewName, HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException;
}
