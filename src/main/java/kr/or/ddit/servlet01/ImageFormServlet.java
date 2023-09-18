package kr.or.ddit.servlet01;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.MessageFormat;

import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/imageForm1.do")
public class ImageFormServlet extends HttpServlet{
	
	private ServletContext application;
	
	@Override
	public void init(ServletConfig config) throws ServletException {
		super.init(config);
		application = getServletContext();
	}
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		resp.setContentType("text/html;charset=utf-8");
		
		String folderPath = application.getInitParameter("mediaFolder"); 
		File folder = new File(folderPath);
		
		String[] imageFiles = folder.list((d,n)-> {
			String mime = application.getMimeType(n);
			return mime !=null && mime.startsWith("image/"); 
		});
		
		StringBuilder html = new StringBuilder();
		                                                              
		html.append("<html>                                            ");
		html.append("	<body>                                         ");
		//html.append("		<form action= '/Webstudy01/image.do'> ");
		html.append(MessageFormat.format("<form action=''{0}/image.do''>", req.getContextPath()));
		
		//html.append("			<select name = 'image'>                ");
		html.append("			<select name = 'image' onchane ='this.form.submit()'>                ");
		// 위에코드는 전송 버튼 누르지 않고 선택만해도 바로 나오게 하는 코드.  
		
		
		for(String tmp :imageFiles) {
		
		//html.append("				<option>cat1.jpg</option>          ");
		html.append(MessageFormat.format("<option>{0}</option>",tmp));
		
		}
		
		html.append("			</select>                              ");
		html.append("			<button type='submit'>전송</button>    ");
		html.append("		</form>                                    ");
		html.append("	</body>                                        ");
		html.append("</html>                                           ");
	
		// since java 1.7
		// try~With~resource : auto close 특성을 가진 문법 
		// try( Closable 객체 선언구문 ){ 자동으로 close가 된다. 
		try(PrintWriter out = resp.getWriter()){
			out.println(html);
		}
		// 코드가 아주 간결하다. 
		
		
		// 1.7미만에서 사용하는 문법형태 
		/*PrintWriter out = resp.getWriter();
		try {
			out = resp.getWriter();
			out.println(html);
		}finally {
			if(out!=null)
				out.close();
		}*/
	}
}
