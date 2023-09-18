package kr.or.ddit.member.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;

import kr.or.ddit.member.service.MemberService;
import kr.or.ddit.member.service.MemberServiceImpl;
import kr.or.ddit.mvc.simple.AbstractController;
import kr.or.ddit.vo.MemberVO;


public class MemberViewController extends AbstractController{

	private MemberService service =  new MemberServiceImpl();
	
	@Override
	public String getHandler(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// 회원정보 조회
		String memId = req.getParameter("who");
		// member에 데이터 저장 
		MemberVO member = service.retrieveMember(memId);
		// 데이터 저장
		req.setAttribute("member", member);
		
		return "member/ajax/memberView";
	}
}
		

