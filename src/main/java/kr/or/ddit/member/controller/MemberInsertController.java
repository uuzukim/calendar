package kr.or.ddit.member.controller;

import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.InvocationTargetException;
import java.util.Date;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.beanutils.ConvertUtils;
import org.apache.commons.beanutils.converters.DateConverter;
import org.apache.commons.lang3.StringUtils;

import com.fasterxml.jackson.databind.ObjectMapper;

import kr.or.ddit.enumpkg.serviceResult;
import kr.or.ddit.member.service.MemberService;
import kr.or.ddit.member.service.MemberServiceImpl;
import kr.or.ddit.mvc.simple.AbstractController;
import kr.or.ddit.util.PopulateUtils;
import kr.or.ddit.validate.ValidateUtils;
import kr.or.ddit.validate.groups.InsertGroup;
import kr.or.ddit.vo.MemberVO;


public class MemberInsertController extends AbstractController {
	private MemberService service = new MemberServiceImpl();

	/**
	 * 가입 양식 제공
	 */
	@Override
	public String getHandler (HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		return "member/memberForm";
	}

	/**
	 * 양식을 통해 입력된 개인 정보 처리
	 */
	@Override
	public String postHandler (HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		MemberVO member = new MemberVO();
		req.setAttribute("member", member);
		Map<String, String[]> parameterMap = req.getParameterMap(); // parametermap으로 쓰는이유? 안그러면 우리가
																	// getparameter(memId)이걸 17번써야하잖아 ? 그게 양이 많아지면 쓰기
																	// 어려우니 parametermap에 담아서

		PopulateUtils.populate(member, parameterMap); // 그걸 다시 member에 담아주는거지 . 손안대고 코풀기~~~

		Map<String, String> errors = new LinkedHashMap<>(); // 누가 검증 통과했는지 모르자나
		req.setAttribute("errors", errors);
		ValidateUtils.validate(member, errors,InsertGroup.class);

		//2. 요청 ...
		String logicalViewName =null;
		if (errors.isEmpty()) {// 검증 통과~!
			serviceResult result = service.createMember(member);
			if(serviceResult.OK == result ) {
				logicalViewName = "redirect:/";
			}else {
				req.setAttribute("message","서버 오류, 잠시 뒤 다시 시도하시오.");
				logicalViewName = "member/memberForm";
			}
		}else {
			logicalViewName="member/meberForm";
		}
		return logicalViewName;
	}
}
	
