package kr.or.ddit.prod.dao;

import java.util.List;

import kr.or.ddit.vo.ProdVO;

/**
 * 상품 관리 CRUD, Persistence Layer 
 *
 */
public interface ProdDAO {

	public int insertProd(ProdVO prod); //
	public ProdVO selectProd(String prodId);//상품조회 없으면 NULL 반환
	public List<ProdVO> selectProdList();
	public int updateProd(ProdVO prod);
}
