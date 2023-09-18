package kr.or.ddit.buyer.controller;

import java.io.IOException;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import kr.or.ddit.buyer.service.BuyerService;
import kr.or.ddit.buyer.service.BuyerServiceImpl;
import kr.or.ddit.enumpkg.serviceResult;
import kr.or.ddit.mvc.simple.AbstractController;
import kr.or.ddit.prod.dao.OthersDAO;
import kr.or.ddit.prod.dao.OthersDaoImpl;
import kr.or.ddit.util.PopulateUtils;
import kr.or.ddit.validate.ValidateUtils;
import kr.or.ddit.validate.groups.InsertGroup;
import kr.or.ddit.vo.BuyerVO;
import kr.or.ddit.vo.LprodVO;

public class BuyerInsertController  extends AbstractController{
	private BuyerService service = new BuyerServiceImpl();
	private OthersDAO othersDAO = new OthersDaoImpl();
	
	/** 
	 * 가입양식제공
	 */
	@Override
	public String getHandler(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		 req.setAttribute("lprodList", othersDAO.selectLprodList());
		return "buyer/buyerForm";
	}
	
	@Override
	public String postHandler(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.setAttribute("lprodList", othersDAO.selectLprodList());
		
		BuyerVO buyer = new BuyerVO();
		req.setAttribute("buyer", buyer);
	
		Map<String, String[]> parameterMap = req.getParameterMap();
		PopulateUtils.populate(buyer, parameterMap);
		
		Map<String, String> errors = new LinkedHashMap<>();	
		req.setAttribute("errors", errors);
		ValidateUtils.validate(buyer, errors, InsertGroup.class);
		
		String logicalViewName = null;
		if(errors.isEmpty()) {
			serviceResult result = service.createBuyer(buyer);
			if(serviceResult.OK == result) {
				logicalViewName = "redirect:/buyer/buyerView.do?what="+buyer.getBuyerId();
			}else {
				req.setAttribute("message", "서버 오류, 잠시 뒤 다시 시도하시오.");
				logicalViewName = "buyer/buyerForm";
			}
		}else {
			logicalViewName="buyer/buyerForm";
		}
		return logicalViewName;
	}
	
}
