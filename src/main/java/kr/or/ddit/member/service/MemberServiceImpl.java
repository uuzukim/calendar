package kr.or.ddit.member.service;

import java.text.MessageFormat;
import java.util.List;

import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;

import kr.or.ddit.enumpkg.serviceResult;
import kr.or.ddit.exception.PKNotFoundException;
import kr.or.ddit.login.AuthenticateException;
import kr.or.ddit.login.service.AuthenticateService;
import kr.or.ddit.login.service.AuthenticateServiceImpl;
import kr.or.ddit.member.dao.MemberDAO;
import kr.or.ddit.member.dao.MemberDAOImpl;
import kr.or.ddit.vo.MemberVO;

public class MemberServiceImpl implements MemberService {

	// 의존관계 형성 --> 결합력 발생 --> 차후 DI(Dependency Injection)구조로 해결.
	private MemberDAO memberDAO = new MemberDAOImpl();
	private AuthenticateService authService = new AuthenticateServiceImpl();
	
	private PasswordEncoder encoder = PasswordEncoderFactories.createDelegatingPasswordEncoder();
	
	@Override
	public serviceResult createMember(MemberVO member) {
		serviceResult result=null;
		try{ // 그런사람 있는지 확인후 없으면
			retrieveMember(member.getMemId());
			result=serviceResult.PKDUPLICATE;
		}catch(PKNotFoundException e) { // 가입시켜야지
			
		encryptMember(member);
		int cnt = memberDAO.insertMember(member);
		result = cnt >0 ? serviceResult.OK : serviceResult.FAIL;
		}
//		if(count>0) {
//			serviceResult result = serviceResult.OK;
//		}
		return result;
	}

	/**
	 * 암호화 하는 메서드. 
	 * 개인을 식별할 수 있는 모든 정보는 보호의 대상. 
	 * @param member
	 */
	private void encryptMember(MemberVO member) {
		String plain = member.getMemPass();
		String encoded = encoder.encode(plain);
		member.setMemPass(encoded);
	}

	@Override
	public MemberVO retrieveMember(String memId) throws PKNotFoundException {
		MemberVO saved = memberDAO.selectMember(memId);
		if (saved == null) {// 존재하지 않은 경우
			throw new PKNotFoundException(MessageFormat.format("{0} 해당사용자없음.", memId));
		}
		return saved;

	}

	@Override
	public List<MemberVO> retrieveMemberList() {
		return memberDAO.selectMemberList();
	}

	@Override
	public serviceResult modifyMember(MemberVO member) {
		serviceResult result = null;
		// 직접 인증하지 않고 로직을 이용하면 장점? 응집력이 높아진다. 
		try {
			authService.authenticate(member);			
			int cnt = memberDAO.updateMember(member);
			result = cnt > 0 ? serviceResult.OK : serviceResult.FAIL;
		}catch (AuthenticateException e) {
			result = serviceResult.INVALIDPASSWORD;
		}
		return result;
	}

	// LA? (레이어드를 쓰는이유가 무엇? ) HCLC(응집력은 높이고 결합력은 낮게)
	// 레이어드들에게 단일 책임을 부여해서 응집력을 높임. 
	// Layer 사이에서 발생하는 의존관계에 따라 결합력이 발생함. 
	
	@Override
	public serviceResult removeMember(MemberVO member) {
		serviceResult result = null;
		// 직접 인증하지 않고 로직을 이용하면 장점? 응집력이 높아진다. 
		// -?
		try {
			authService.authenticate(member);			
			int cnt = memberDAO.deleteMember(member.getMemId());
			result = cnt > 0 ? serviceResult.OK : serviceResult.FAIL;
		}catch (AuthenticateException e) {
			result = serviceResult.INVALIDPASSWORD;
		}
		return result;
	}

}
