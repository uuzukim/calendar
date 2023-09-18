package kr.or.ddit.prod.service;

import java.text.MessageFormat;
import java.util.List;

import kr.or.ddit.enumpkg.serviceResult;
import kr.or.ddit.exception.PKNotFoundException;
import kr.or.ddit.prod.dao.ProdDAO;
import kr.or.ddit.prod.dao.ProdDaoImpl;
import kr.or.ddit.vo.ProdVO;

public class ProdServiceImpl implements ProdService {
	
	private ProdDAO prodDAO = new ProdDaoImpl();
	@Override
	public serviceResult createProd(ProdVO prod) {
		
		int cnt = prodDAO.insertProd(prod);
		serviceResult result = cnt >0? serviceResult.OK : serviceResult.FAIL;
		return result;
	}

	@Override
	public ProdVO retrieveProd(String prodId) throws PKNotFoundException {
		ProdVO prod=prodDAO.selectProd(prodId);
		if(prod==null)
			throw new PKNotFoundException(MessageFormat.format("{0} 해당 상품 없음", prodId));
		return prod;
		
	}

	@Override
	public List<ProdVO> retrieveProdList() {
		
		return prodDAO.selectProdList();
	}

	@Override
	public serviceResult modifyProd(ProdVO prod) {
		
		int cnt = prodDAO.updateProd(prod);
		serviceResult result = cnt >0? serviceResult.OK : serviceResult.FAIL;
		return result;
	}

}
