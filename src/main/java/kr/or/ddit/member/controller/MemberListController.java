package kr.or.ddit.member.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;

import kr.or.ddit.member.service.MemberService;
import kr.or.ddit.member.service.MemberServiceImpl;
import kr.or.ddit.mvc.simple.AbstractController;
import kr.or.ddit.mvc.simple.AbstractControllerHandlerAdapter;
import kr.or.ddit.vo.MemberVO;


public class MemberListController extends AbstractController{
	
	//1. 서비스 객체 생성하기. 의존관계 형성
	private MemberService service = new MemberServiceImpl();
	
	// 내가만든 컨트롤러를 핸들러어댑터가 사용. 얘를 호출한건 핸들러어댑터. 파라미터는 누가 넣어준거야 핸들러? 
	public String getHandler(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
	//2. 회원정보 조회 
	List<MemberVO> memberList = service.retrieveMemberList();
	
	//3. 화면 출력을 위한 데이터 저장
	req.setAttribute("memberList", memberList);
	
	//4. 해당 화면으로 포워딩
	return "member/memberList";
	}
}
