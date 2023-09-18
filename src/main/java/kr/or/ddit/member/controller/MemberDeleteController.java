package kr.or.ddit.member.controller;

import java.io.IOException;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import kr.or.ddit.enumpkg.serviceResult;
import kr.or.ddit.member.service.MemberService;
import kr.or.ddit.member.service.MemberServiceImpl;
import kr.or.ddit.util.PopulateUtils;
import kr.or.ddit.validate.ValidateUtils;
import kr.or.ddit.validate.groups.DeleteGroup;
import kr.or.ddit.validate.groups.UpdateGroup;
import kr.or.ddit.vo.MemberVO;
import oracle.net.ns.SessionAtts;

@WebServlet("/member/memberDelete.do")
public class MemberDeleteController extends HttpServlet{

	private MemberService service = new MemberServiceImpl();
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		HttpSession session = req.getSession();
		
		req.setCharacterEncoding("utf-8");
		String memPass = req.getParameter("password");
		
		
		MemberVO authMember = (MemberVO) req.getSession().getAttribute("authMember");
		String memId = authMember.getMemId();
		MemberVO inputData = new MemberVO();
		inputData.setMemId(authMember.getMemId());
		inputData.setMemPass(memPass);
		
		
		Map<String, String> errors = new HashMap<>(); 
		req.setAttribute("errors", errors);
		
		ValidateUtils.validate(inputData, errors, DeleteGroup.class);
		
		String viewName = null;
		
		String logicalViewName =null;
		if (errors.isEmpty()) {// 검증 통과~!
			serviceResult result = service.removeMember(inputData);//여기에 authmember넘기면 큰일나지?
			
			switch (result) {
				case INVALIDPASSWORD:
					session.setAttribute("message","비밀번호 오류");
					//ㄴ플래시 어트리뷰트 방식 : 한번 띄우고 삭제.
					viewName = "redirect:/mypage";
					break;
				case OK:	
					session.invalidate();
					viewName = "redirect:/"; 
					break;
				default:
					req.setAttribute("message","서버 오류, 잠시 뒤 다시 시도하시오.");
					viewName = "redirect:/mypage";
					break;
			}
		} else { // 검증 통과 못함-> 비밀번호를 다시 입력할 수 있는 곳으로 보내야해 . 
			session.setAttribute("message", "비밀번호 누락");
			viewName = "redirect:/mypage";
		}
		
		
		
		if (viewName.startsWith("redirect:")) {// 성공시야
			viewName = viewName.substring("redirect:".length());
			resp.sendRedirect(req.getContextPath() + viewName);// redirect가는 이유? 이건 사실 선택사항인데 req, resp지우고 새로 다시 get메서드로
																// 가서 get메서드에서 다시 list를 업데이트해서 받아와 그럼 결과적으로는 새리스트가 출력되겠지
		} else { // 실패시
			req.getRequestDispatcher(viewName).forward(req, resp); // 실패하면
		}
	}
}
