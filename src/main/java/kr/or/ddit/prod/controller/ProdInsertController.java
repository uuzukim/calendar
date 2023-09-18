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

import kr.or.ddit.enumpkg.serviceResult;
import kr.or.ddit.mvc.simple.AbstractController;
import kr.or.ddit.prod.dao.OthersDAO;
import kr.or.ddit.prod.dao.OthersDaoImpl;
import kr.or.ddit.prod.service.ProdService;
import kr.or.ddit.prod.service.ProdServiceImpl;
import kr.or.ddit.util.PopulateUtils;
import kr.or.ddit.validate.ValidateUtils;
import kr.or.ddit.validate.groups.InsertGroup;
import kr.or.ddit.vo.BuyerVO;
import kr.or.ddit.vo.LprodVO;
import kr.or.ddit.vo.ProdVO;

public class ProdInsertController extends AbstractController{
	private ProdService service = new ProdServiceImpl();
	private OthersDAO othersDAO = new OthersDaoImpl();
	
	@Override
	public String getHandler(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		         List<LprodVO> lprodList = othersDAO.selectLprodList();
		         List<BuyerVO> buyerList = othersDAO.selectBuyerList();
		         req.setAttribute("lprodList", lprodList);
		         req.setAttribute("buyerList", buyerList);
		         return "prod/prodForm";
	}

	@Override
	public String postHandler(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		ProdVO prod = new ProdVO();
		req.setAttribute("prod", prod);

		PopulateUtils.populate(prod, req.getParameterMap()); // 오ㅐ 여기에느 prodid가 없고

		Map<String, String> errors = new LinkedHashMap<>();
		req.setAttribute("errors", errors);
		ValidateUtils.validate(prod, errors, InsertGroup.class);

		String logicalViewName = null;
		if (errors.isEmpty()) {
			serviceResult result = service.createProd(prod); // 여기서 prodid가 넣어진거야?
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

//	private ProdService service = new ProdServiceImpl();
//	/**
//	 * 입력 양식 제공
//	 */
//	@Override
//	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
//		
//		String logicalViewName = "prod/prodForm";
//		String viewName = "/" + logicalViewName + ".tiles";
//		req.getRequestDispatcher(viewName).forward(req, resp);
//	}
//	
//	/**
//	 * 양식을 통해 입력된 개인 정보 처리
//	 */
//	@Override
//	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
//		req.setCharacterEncoding("utf-8");
//		
//		ProdVO prod = new ProdVO();
//		req.setAttribute("prod", prod);
//		
//		Map<String, String[]> parameterMap = req.getParameterMap();
//		
//		PopulateUtils.populate(prod, parameterMap);
//		
//		Map<String, String> errors = new LinkedHashMap<>(); // 누가 검증 통과했는지 모르자나
//		req.setAttribute("errors", errors);
//		
//		String viewName = null;
//		String logicalViewName =null;
//		
//		if(errors.isEmpty()) {
//			serviceResult result = service.createProd(prod);
//			
//			switch (result) {
//			case PKDUPLICATE:
//				req.setAttribute("message","상품코드 중복");
//				logicalViewName = "prod/prodForm";
//				viewName = "/" + logicalViewName + ".tiles";
//				break;
//			case OK:	
//				viewName = "redirect:/";// 이렇게 하면 에러와 뭐가 사라진다고? .. 
//				break;
//			default:
//				req.setAttribute("message","서버 오류, 잠시 뒤 다시 시도하시오.");
//				logicalViewName = "prod/prodForm";
//				viewName = "/" + logicalViewName + ".tiles";
//				break;
//			}
//			}else {
//				logicalViewName = "prod/prodForm";
//				viewName = "/" + logicalViewName + ".tiles";
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
