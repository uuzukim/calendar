package kr.or.ddit.buyer.dao;

import java.util.List;

import kr.or.ddit.vo.BuyerVO;
import kr.or.ddit.vo.ProdVO;

/**
 * 거래처 관리 CRUD, Persistence Layer 
 *
 */
public interface BuyerDAO {
	public int insertBuyer(BuyerVO buyer); //
	public BuyerVO selectBuyer(String buyerId);//상품조회 없으면 NULL 반환
	public List<BuyerVO> selectBuyerList();
	public int updateBuyer(BuyerVO buyer);
	public int deleteBuyer(String buyerId);
}
