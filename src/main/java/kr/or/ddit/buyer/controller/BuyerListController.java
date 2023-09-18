package kr.or.ddit.buyer.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import kr.or.ddit.buyer.service.BuyerService;
import kr.or.ddit.buyer.service.BuyerServiceImpl;
import kr.or.ddit.mvc.simple.AbstractController;
import kr.or.ddit.vo.BuyerVO;


public class BuyerListController extends AbstractController{

	private BuyerService service = new BuyerServiceImpl();
	@Override
	public String getHandler(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		//정보조회
		List<BuyerVO> buyerList = service.retrieveBuyerList();
		
		//정보저장
		req.setAttribute("buyerList", buyerList);
		
		//전달화면으로 포워딩
		return "buyer/buyerList";
	}
}
