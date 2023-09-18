package kr.or.ddit.prod.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;

import kr.or.ddit.mvc.simple.AbstractController;
import kr.or.ddit.prod.service.ProdService;
import kr.or.ddit.prod.service.ProdServiceImpl;
import kr.or.ddit.vo.ProdVO;

public class ProdViewController extends AbstractController{
   private ProdService service = new ProdServiceImpl();
 
   @Override
   public String getHandler(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
      
      String prodId = req.getParameter("what");
      if(StringUtils.isBlank(prodId)) {
         resp.sendError(400, "필수 파라미터 누락");
         return null; // 그럼 이걸 프론트컨트롤러가 가져가겠지? 
      }
      
      ProdVO prod = service.retrieveProd(prodId);
      
      req.setAttribute("prod", prod);
      
      return "prod/prodView";
      
   }

}