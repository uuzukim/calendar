package kr.or.ddit.mvc.simple;

import javax.servlet.http.HttpServletRequest;

/**
 * 현재 요청을 처리할 command handler(Back-end Controller)를 검색함. 
 * 파라미터로 지금 그 요청을 받고 어떤 핸들러를 쓸지 확인함
 */
public interface HandlerMapping {
	
	/**
	 * command handler 검색 
	 * @param req
	 * @return 검색에 실패한 경우, null 반환
	 */
	public AbstractController findCommandHandler(HttpServletRequest req);
}
