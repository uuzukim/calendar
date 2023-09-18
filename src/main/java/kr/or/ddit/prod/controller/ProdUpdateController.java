package kr.or.ddit.prod.controller;

import java.io.IOException;
import java.text.MessageFormat;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;

import kr.or.ddit.enumpkg.serviceResult;
import kr.or.ddit.mvc.simple.AbstractController;
import kr.or.ddit.prod.dao.OthersDAO;
import kr.or.ddit.prod.dao.OthersDaoImpl;
import kr.or.ddit.prod.service.ProdService;
import kr.or.ddit.prod.service.ProdServiceImpl;
import kr.or.ddit.util.PopulateUtils;
import kr.or.ddit.validate.ValidateUtils;
import kr.or.ddit.validate.groups.InsertGroup;
import kr.or.ddit.validate.groups.UpdateGroup;
import kr.or.ddit.vo.BuyerVO;
import kr.or.ddit.vo.LprodVO;
import kr.or.ddit.vo.MemberVO;
import kr.or.ddit.vo.ProdVO;


public class ProdUpdateController extends AbstractController {

	private ProdService service = new ProdServiceImpl();
	private OthersDAO othersDAO = new OthersDaoImpl();
	
	@Override
	public String getHandler(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		       req.setAttribute("lprodList", othersDAO.selectLprodList());
		       req.setAttribute("buyerList", othersDAO.selectBuyerList());
		         
		         String prodId = req.getParameter("what");
		         if(StringUtils.isBlank(prodId)) {
		 			resp.sendError(400,"필수 파라미터 누락");
		 			return null;
		 		}
		         ProdVO prod = service.retrieveProd(prodId);
		         req.setAttribute("prod", prod);
		         
		         
		         return "prod/prodForm";
	}

	@Override
	public String postHandler(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		
		 req.setAttribute("lprodList", othersDAO.selectLprodList());
         req.setAttribute("buyerList", othersDAO.selectBuyerList());
		
		ProdVO prod = new ProdVO();
		req.setAttribute("prod", prod);

		PopulateUtils.populate(prod, req.getParameterMap()); // 오ㅐ 여기에느 prodid가 없고

		Map<String, String> errors = new LinkedHashMap<>();
		req.setAttribute("errors", errors);
		
		ValidateUtils.validate(prod, errors, UpdateGroup.class);

		String logicalViewName = null;
		if (errors.isEmpty()) {
			serviceResult result = service.modifyProd(prod); // 여기서 prodid가 넣어진거야?
			if (serviceResult.OK == result) {
				logicalViewName = "redirect:/prod/prodView.do?what=" + prod.getProdId();
			} else {
				req.setAttribute("message", "서버 오류, 잠시 뒤 다시 시도하세요.");
				logicalViewName = "prod/prodForm";
			}
		} else {
			logicalViewName = "prod/prodForm";
		}

		return logicalViewName;
	}

}	
	
	
	
//	@Override
//	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
//		req.setCharacterEncoding("utf-8");
//		
//		String prodId= req.getParameter("what");
//		if(StringUtils.isBlank(prodId)) {
//			resp.sendError(400,"필수 파라미터 누락");
//			return;
//		}
//		
//		ProdVO prod = (ProdVO) req.getSession().getAttribute("prod");
//		ProdVO detail = service.retrieveProd(prodId);
//		
//		req.setAttribute("prod", detail);
//		
//		String logicalViewName = "member/memberForm";
//		String viewName = "/" + logicalViewName + ".tiles";
//		req.getRequestDispatcher(viewName).forward(req, resp);
//	}
//	
//	
//	@Override
//	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
//		req.setCharacterEncoding("utf-8");
//		
//		ProdVO prod = (ProdVO) req.getAttribute("prod");
//		prod.setProdId(prod.getProdId());
//		req.setAttribute("prod", prod);
//		
//		Map<String, String[]> parameterMap = req.getParameterMap();
//		
//		PopulateUtils.populate(prod, parameterMap);
//		
//		Map<String, String> errors = new LinkedHashMap<>(); // 누가 검증 통과했는지 모르자나
//		req.setAttribute("errors", errors);
//		ValidateUtils.validate(prod, errors, UpdateGroup.class);
//		
//		String viewName = null;
//		String logicalViewName =null;
//		if (errors.isEmpty()) {// 검증 통과~!
//			serviceResult result = service.modifyProd(prod);
//			
//			switch (result) {
//				case INVALIDPASSWORD:
//					req.setAttribute("message","비밀번호 오류");
//					logicalViewName = "prod/updateForm";
//					viewName = "/" + logicalViewName + ".tiles";
//					break;
//				case OK:	
//					viewName = "redirect:/"; 
//					break;
//				default:
//					// 디스패치 방식 
//					req.setAttribute("message","서버 오류, 잠시 뒤 다시 시도하시오.");
//					logicalViewName = "prod/updateForm";
//					viewName = "/" + logicalViewName + ".tiles";
//					break;
//			}
//		} else { // 검증 통과 못함 ㅠ
//			logicalViewName = "prod/updateForm";
//			viewName = "/" + logicalViewName + ".tiles";
//			// errors와 member를 req로 같이 가지고 간다.
//		}
//		if (viewName.startsWith("redirect:")) {// 성공시야
//			viewName = viewName.substring("redirect:".length());
//			resp.sendRedirect(req.getContextPath() + viewName);// redirect가는 이유? 이건 사실 선택사항인데 req, resp지우고 새로 다시 get메서드로
//																// 가서 get메서드에서 다시 list를 업데이트해서 받아와 그럼 결과적으로는 새리스트가 출력되겠지
//		} else { // 실패시
//			req.getRequestDispatcher(viewName).forward(req, resp); // 실패하면
//		}
//	}	
//}
