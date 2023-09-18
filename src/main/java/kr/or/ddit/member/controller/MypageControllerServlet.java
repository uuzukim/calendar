package kr.or.ddit.member.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import kr.or.ddit.member.service.MemberService;
import kr.or.ddit.member.service.MemberServiceImpl;
import kr.or.ddit.vo.MemberVO;

@WebServlet("/mypage")
public class MypageControllerServlet extends HttpServlet {

	// service가져오기
	private MemberService service = new MemberServiceImpl();

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		req.setCharacterEncoding("utf-8");

		// 회원정보 조회
		MemberVO authMember = (MemberVO) req.getSession().getAttribute("authMember");
		MemberVO detail = service.retrieveMember(authMember.getMemId());

		// 데이터 저장
		req.setAttribute("member", detail);

		// 해당화면 포워딩
		String logicalViewName = "member/mypage";
		String viewName = "/" + logicalViewName + ".tiles";
		req.getRequestDispatcher(viewName).forward(req, resp);

	}

}
