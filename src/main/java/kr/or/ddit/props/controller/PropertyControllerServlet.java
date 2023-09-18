package kr.or.ddit.props.controller;

import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.apache.ibatis.mapping.ResultMap;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.PropertyName;

import kr.or.ddit.props.service.PropertyService;
import kr.or.ddit.props.service.PropertyServiceImpl;
import kr.or.ddit.vo.PropertyVO;

@WebServlet("/property")
public class PropertyControllerServlet extends HttpServlet{
	private PropertyService service = new PropertyServiceImpl();
		
	
	@Override
	public void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.setCharacterEncoding("utf-8");
		super.service(req, resp);
	}
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException { //읽는것은 get메서드를 쓴다. 
		// 2가지 명령 처리하고있어. 
		String accept = req.getHeader("accept");
		
		String viewName = null;
		if(accept.contains("json")) {// if 를 타면 데이터를 만들어지는거고 model이 만들어지는거야.
			String propertyName = req.getParameter("what"); // what파라미터가 없으면 전체조회
			if(StringUtils.isBlank(propertyName)) {
				List<PropertyVO> propList =  service.retrieveProperties();// selectall
				req.setAttribute("propList", propList);
			}else { // 이건 파라미터가 있다는거지? 그럼 선택조회 
				PropertyVO prop = service.retrieveProperty(propertyName);
				req.setAttribute("prop", prop);
			}
			// 이제 jsonview를 이용해 마샬린 해야해 
			viewName="/jsonView.view";
		}else { // ui를 만드는것 
			String logicalViewName ="props/singleViewCase2";
			viewName="/"+logicalViewName+".tiles";
		}
		
		req.getRequestDispatcher(viewName).forward(req,resp);
	}
	
	private boolean validate(PropertyVO propVO,Map<String, String>errors) { // 유효성 검증
		boolean valid = true;
		if(StringUtils.isBlank(propVO.getPropertyName())) {
			valid=false;
			errors.put("propertyName", "필수 파라미터 누락");
		}
		if(StringUtils.isBlank(propVO.getPropertyValue())) {
			valid=false;
			errors.put("propertyValue", "필수 파라미터 누락");
		}
		return valid;
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		PropertyVO propVO = null;
		try(
			InputStream is = req.getInputStream();
		){
			propVO = new ObjectMapper().readValue(is, PropertyVO.class);
		}
		Map<String, String> errors = new HashMap<>(); //ex) 300번지 객체.
		boolean valid = validate(propVO, errors);
		if(!errors.isEmpty()) {// 검증확인  ,  error가 비어있으면
			resp.sendError(400,errors.toString());
			return;
		}
		// call by reference는 메소드에서 만들어진 메시지가 하나가 아닐때 사용
		
		//call by reference 반환값의 형태가 아니라도 메소드 안에서 만들어진 파라미터 형태로 가져올수있따.
		boolean success = service.createProperty(propVO);
		//call by reference때문에 반환타입이 propvo가 아니더라도 매개변수로 propvo로 받아와서 prop3 id를 받아올수 있는것. 
		
		Map<String, Object> resultMap = new HashMap<>();
		resultMap.put("prop", propVO);
		resultMap.put("success", success);
		req.setAttribute("result", resultMap);// 응답이 나가면 없어짐
		String viewName = null;
		if(success) { // if문을타면 result메서드, req사라짐
			viewName ="redirect:/property";  // get메서드로감. 
		}else { // 얘는 살려야겠지? 못탔으니까? 그래서 jsonview를 사용해...?
			viewName = "/jsonView.view"; // 실패한 이유와 정보를 출력해주기위해 마샬린해서 내보내기위해 보낸다. 
		}
		
		if(viewName.startsWith("redirect:")) {// 성공시야
			viewName= viewName.substring("redirect:".length());
			resp.sendRedirect(req.getContextPath() + viewName);// redirect가는 이유? 이건 사실 선택사항인데 req, resp지우고 새로 다시 get메서드로 가서 get메서드에서 다시 list를 업데이트해서 받아와 그럼 결과적으로는 새리스트가 출력되겠지
		}else { // 실패시 
			req.getRequestDispatcher(viewName).forward(req,resp); // 실패하면 
		}
	}
	
	
	
//	@Override
//	protected void doPut(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
//		Map<String, Object> resultMap = new HashMap<>();
//		PropertyVO propVO = null;
//		try(
//			InputStream is = req.getInputStream();
//		){
//			PropertyVO prop = new ObjectMapper().readValue(is,PropertyVO.class);
//			prop = 
//			resultMap.put("prop", prop);
//			
//		}
//		
////		String propertyName= req.getParameter("propertyName");
//		
//		if(StringUtils.isBlank(propertyName)) {
//			resp.sendError(400,"필수파라미터 누락");
//			return;
//		}
//
//		boolean success = service.modiProperty(null)
//		
//		
//		resultMap.put("success", success);
//		req.setAttribute("result", resultMap);
//		String viewName = null;
////		if(success) {
////			viewName = "redirect:/property"; // delete
////		}else {
//			viewName="/jsonView.view";
////		}
//		if(viewName.startsWith("redirect:")) {// 성공시야
//			viewName= viewName.substring("redirect:".length());
//			resp.sendRedirect(req.getContextPath() + viewName);// redirect가는 이유? 이건 사실 선택사항인데 req, resp지우고 새로 다시 get메서드로 가서 get메서드에서 다시 list를 업데이트해서 받아와 그럼 결과적으로는 새리스트가 출력되겠지
//		}else { // 실패시 
//			req.getRequestDispatcher(viewName).forward(req,resp); // 실패하면 
//		}
//	}
	
	
	
	@Override
	protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		Map<String, Object> resultMap = new HashMap<>();
		String propertyName = null;
		try(
			InputStream is = req.getInputStream();
		){
			PropertyVO prop = new ObjectMapper().readValue(is,PropertyVO.class);
			propertyName = prop.getPropertyName();
			resultMap.put("prop", prop);
			
		}
		
//		String propertyName= req.getParameter("propertyName");
		
		if(StringUtils.isBlank(propertyName)) {
			resp.sendError(400,"필수파라미터 누락");
			return;
		}

		boolean success = service.removeProperty(propertyName);
		
		
		resultMap.put("success", success);
		req.setAttribute("result", resultMap);
		String viewName = null;
//		if(success) {
//			viewName = "redirect:/property"; // delete
//		}else {
			viewName="/jsonView.view";
//		}
		if(viewName.startsWith("redirect:")) {// 성공시야
			viewName= viewName.substring("redirect:".length());
			resp.sendRedirect(req.getContextPath() + viewName);// redirect가는 이유? 이건 사실 선택사항인데 req, resp지우고 새로 다시 get메서드로 가서 get메서드에서 다시 list를 업데이트해서 받아와 그럼 결과적으로는 새리스트가 출력되겠지
		}else { // 실패시 
			req.getRequestDispatcher(viewName).forward(req,resp); // 실패하면 
		}
	}
}
