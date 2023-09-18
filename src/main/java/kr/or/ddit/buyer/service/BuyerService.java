package kr.or.ddit.buyer.service;

import java.util.List;

import kr.or.ddit.enumpkg.serviceResult;
import kr.or.ddit.exception.PKNotFoundException;
import kr.or.ddit.vo.BuyerVO;


/**
 *	거래처 관리 CRUD, Business Logic Layer 
 *
 */
public interface BuyerService {

		/**
		 * @para prod
		 * @return ok, fail
		 */
		public serviceResult createBuyer(BuyerVO buyer);// 여기서 발생할 경우의 수는 두가지 왜? 클라이언트가 무작위로 넣는게 아니래 관리자가 넣는것이기 때문 
		
		/**
		 * 
		 * @param prodId
		 * @return
		 * @throws PKNotFoundException 해당 바이어가 없는 경우 
		 */
		public BuyerVO retrieveBuyer(String buyerId)throws PKNotFoundException; //
		public List<BuyerVO> retrieveBuyerList();
		
		/**
		 * 
		 * @param prod
		 * @return 여기서는 인증 2가지 OK, FAIL. 비밀번호 받을일 없잖아? 상품이?
		 */
		public serviceResult modifyBuyer(BuyerVO buyer);//
		
		public serviceResult removeBuyer(String buyer);
}
