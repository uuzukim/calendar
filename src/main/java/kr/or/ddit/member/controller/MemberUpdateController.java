package kr.or.ddit.member.controller;

import java.io.IOException;
import java.util.LinkedHashMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;

import kr.or.ddit.enumpkg.serviceResult;
import kr.or.ddit.member.service.MemberService;
import kr.or.ddit.member.service.MemberServiceImpl;
import kr.or.ddit.mvc.simple.AbstractController;
import kr.or.ddit.util.PopulateUtils;
import kr.or.ddit.validate.ValidateUtils;
import kr.or.ddit.validate.groups.UpdateGroup;
import kr.or.ddit.vo.MemberVO;


public class MemberUpdateController extends AbstractController{
	
	private MemberService service = new MemberServiceImpl();
	
	@Override
	public String getHandler(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		MemberVO authMember = (MemberVO) req.getSession().getAttribute("authMember");
		MemberVO detail = service.retrieveMember(authMember.getMemId());
		
		req.setAttribute("member", detail);
	
		return "member/memberForm";
	
	}
	
	@Override
	public String postHandler (HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		MemberVO authMember = (MemberVO) req.getSession().getAttribute("authMember");
		
		MemberVO member = new MemberVO();
		member.setMemId(authMember.getMemId());
		req.setAttribute("member", member);
		// member.setMemId(req.getParameter("memId"));
		Map<String, String[]> parameterMap = req.getParameterMap(); // parametermap으로 쓰는이유? 안그러면 우리가
																	// getparameter(memId)이걸 17번써야하잖아 ? 그게 양이 많아지면 쓰기
		PopulateUtils.populate(member, parameterMap); // 그걸 다시 member에 담아주는거지 . 손안대고 코풀기~~~
		// ㄴ여기에는 아이디가 없을수도 있음. 
		
		Map<String, String> errors = new LinkedHashMap<>(); // 누가 검증 통과했는지 모르자나
		req.setAttribute("errors", errors);
		ValidateUtils.validate(member, errors, UpdateGroup.class); // 상황에 따라 검증 조건이 달라짐. 

		//2. 요청 ...
		String logicalViewName =null;
		if (errors.isEmpty()) {// 검증 통과~!
			serviceResult result = service.modifyMember(member);
			
			if(serviceResult.OK == result) {
				logicalViewName = "member/memberForm";
			}else {
				req.setAttribute("message","비밀번호 오류");
				logicalViewName = "member/memberForm";
			}
		}else {
			logicalViewName = "member/memberForm";
		}
		return logicalViewName;
	}	
}
