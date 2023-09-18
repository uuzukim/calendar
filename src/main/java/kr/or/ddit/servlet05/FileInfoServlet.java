package kr.or.ddit.servlet05;

import java.io.File;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.text.MessageFormat;

import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/server/fileInfo")
public class FileInfoServlet extends HttpServlet{
	private ServletContext application;
	
	@Override
	public void init(ServletConfig config) throws ServletException {
		super.init(config);
		application = getServletContext();
	}
	
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.setCharacterEncoding("utf-8");
		
		String target = req.getParameter("target");
		if(target==null || target.isEmpty()) {
			resp.sendError(400,"필수 파라미터 누락");
			return;
		}
		
		String realPath = application.getRealPath(target);
		
		File targetFile = new File(realPath);
		if(!targetFile.exists()) {
			resp.sendError(404,MessageFormat.format("{0}파일은 없음.", target));
			return;
		}
		
		FileAdapter information = new FileAdapter(targetFile, application);
		
		req.setAttribute("fileInfo", information);
		
		
		String viewName = "/jsonView.view";
		req.getRequestDispatcher(viewName).forward(req,resp);
	}
}
	