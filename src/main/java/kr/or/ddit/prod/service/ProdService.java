package kr.or.ddit.prod.service;

import java.util.List;

import kr.or.ddit.enumpkg.serviceResult;
import kr.or.ddit.exception.PKNotFoundException;
import kr.or.ddit.vo.ProdVO;

/**
 *	상품 관리 CRUD, Business Logic Layer 
 *
 */
public interface ProdService {
	/**
	 * @para prod
	 * @return ok, fail
	 */
	public serviceResult createProd(ProdVO prod);// 여기서 발생할 경우의 수는 두가지 왜? 클라이언트가 무작위로 넣는게 아니래 관리자가 넣는것이기 때문 
	
	/**
	 * 
	 * @param prodId
	 * @return
	 * @throws PKNotFoundException 해당 상품이 없는 경우 
	 */
	public ProdVO retrieveProd(String prodId)throws PKNotFoundException; //
	public List<ProdVO> retrieveProdList();//
	/**
	 * 
	 * @param prod
	 * @return 여기서는 인증 2가지 OK, FAIL. 비밀번호 받을일 없잖아? 상품이?
	 */
	public serviceResult modifyProd(ProdVO prod);//
}
