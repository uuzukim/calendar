package kr.or.ddit.servlet01;

import java.io.File;
import java.io.IOException;

import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/imageForm3.do")
public class ImageFromControllerServlet extends HttpServlet{
	private ServletContext application;
	
	@Override
	public void init(ServletConfig config) throws ServletException {
		super.init(config);
		application = getServletContext();
	}
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String folderPath = application.getInitParameter("mediaFolder"); 
		File folder = new File(folderPath);
		
		
		String[] imageFiles = folder.list((d,n)-> {
			String mime = application.getMimeType(n);
			return mime !=null && mime.startsWith("image/"); 
		});
		
		req.setAttribute("imageFiles",imageFiles);
		String viewName = "/WEB-INF/views/images/imageForm3.jsp";
		req.getRequestDispatcher(viewName).forward(req,resp);
	}
}
