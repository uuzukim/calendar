package kr.or.ddit.login.service;

import kr.or.ddit.login.AuthenticateException;
import kr.or.ddit.vo.MemberVO;

/**
 *	인증 시스템을 위한 Business Logic Layer
 */
public interface AuthenticateService {
	/**
	 * 인증 여부를 판단하기 위한 로직
	 * @param inputData
	 * @return 인증에 성공한 사용자에 대한 정보 반환(아이디, 비밀번호, 이메일, 휴대폰번호, 이름)
	 * @throws AuthenticateException 인증실패시 
	 */
	public MemberVO authenticate(MemberVO inputData) throws AuthenticateException;
	// 정상적으로 memebervo가 반환될거야 실패하면 인증실패가 나타나겠지
	
}
