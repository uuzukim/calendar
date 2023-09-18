package kr.or.ddit.login.service;

import java.text.MessageFormat;

import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;

import kr.or.ddit.login.AuthenticateException;
import kr.or.ddit.member.dao.MemberDAO;
import kr.or.ddit.member.dao.MemberDAOImpl;
import kr.or.ddit.vo.MemberVO;

public class AuthenticateServiceImpl implements AuthenticateService {
	private MemberDAO dao= new MemberDAOImpl();
	private PasswordEncoder encoder = PasswordEncoderFactories.createDelegatingPasswordEncoder();
	@Override
	public MemberVO authenticate(MemberVO inputData) throws AuthenticateException {
		MemberVO saved = dao.selectMemberForAuth(inputData.getMemId()); // 
		if(saved==null)
			throw new AuthenticateException(MessageFormat.format("{0} 해당 사용자는 없음.",inputData.getMemId()));
		String inputPass = inputData.getMemPass();
		String savedPass = saved.getMemPass();
		if(encoder.matches(inputPass, savedPass)) {
			return saved;
		}else {
			throw new AuthenticateException("비밀번호 오류");
		}
	}

}
