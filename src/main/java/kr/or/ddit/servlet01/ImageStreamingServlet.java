package kr.or.ddit.servlet01;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;

import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class ImageStreamingServlet extends HttpServlet{

	private String folderPath;
	private ServletContext application;

	@Override
	public void init(ServletConfig config) throws ServletException {
		super.init(config);
		application = getServletContext();
		application.getInitParameter(folderPath);
		folderPath = application.getInitParameter("mediaFolder"); // 이제 이 mediaFolder파라미터는 어디에서든 쓸수 ㅇ씨는 파라미터가 됨 
		}
	
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		//String folderPath = "E:/medias/images";
		//String fileName ="cat1.jpg";
		String fileName =req.getParameter("image");
		
		if (fileName == null || fileName.isEmpty()) {
			resp.sendError(HttpServletResponse.SC_BAD_REQUEST);
			// sendError에러 상태코드를 내가 정하겠다. 
			//HttpServletResponse.SC_BAD_REQUEST 클라이언트가 요청을 잘못했다. 
			// 이러한 작업을 요청검증이라한다. 
			
			return;
		}
		
		File imageFile = new File(folderPath, fileName);
		
		// 이제 imageFile이 있냐 없냐를 확인.
		if(!imageFile.exists()) { //여기서 true가 나오면 이미지가 없는것. 
			resp.sendError(HttpServletResponse.SC_NOT_FOUND); // 이렇게하면 404가나옴. 원래는 페이지를 로드할수없습니다였음. 
			return;
		}
		
//		MIME(Multipuposed Internet Mail Extension)
		String mime = getServletContext().getMimeType(fileName);
		//String mime="image/jpeg";위에 걸 하드코딩한게 이거야. 
		
		if(mime== null || !mime.startsWith("image/")) {
			resp.sendError(HttpServletResponse.SC_UNSUPPORTED_MEDIA_TYPE);
			return;
			
		}
		
		
		resp.setContentType(mime);
		resp.setContentLengthLong(imageFile.length());
		FileInputStream fis=null;
		OutputStream os = null;
		
		try {
		fis = new FileInputStream(imageFile);
		os = resp.getOutputStream();
//		stream(byte/char) copy
		byte[] buffer = new byte[1024];
		int cnt = -1; //EOF, EOS문자 (-1, null)
		while((cnt = fis.read(buffer)) != -1) {
			os.write(buffer,0,cnt);
		} //만약 2.5바이트면 마지막은 0.5바이트만 받을건데 그럼 나머지 0.5는 ?? 전에께 출력되겠지 . 그래서 뒤에 cnt만큼이라고 써주는것 .
		}finally {
			if(fis!=null)
				fis.close();
			if(os != null)
				os.close();
		}
//		1. request 입력 
//		2. 처리
//		3. response 출력
	}
	}
	
	