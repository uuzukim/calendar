package kr.or.ddit.mvc.simple;

import java.io.IOException;
import java.text.MessageFormat;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public abstract class AbstractController {
	
	public String getHandler(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException{
		resp.sendError(405, MessageFormat.format("{0} 해당 메소드는 지원하지 않습니다.", req.getMethod()));
		return null; 
	}
	
	public String postHandler(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException{
		resp.sendError(405, MessageFormat.format("{0} 해당 메소드는 지원하지 않습니다.", req.getMethod()));
		return null; 
	}
	
	public String putHandler(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException{
		resp.sendError(405, MessageFormat.format("{0} 해당 메소드는 지원하지 않습니다.", req.getMethod()));
		return null; 
	}
	
	public String deleteHandler(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException{
		resp.sendError(405, MessageFormat.format("{0} 해당 메소드는 지원하지 않습니다.", req.getMethod()));
		return null; 
	}
}
