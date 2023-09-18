package kr.or.ddit.buyer.service;

import java.text.MessageFormat;
import java.util.List;

import kr.or.ddit.buyer.dao.BuyerDAO;
import kr.or.ddit.buyer.dao.BuyerDAOImpl;
import kr.or.ddit.enumpkg.serviceResult;
import kr.or.ddit.exception.PKNotFoundException;
import kr.or.ddit.vo.BuyerVO;

public class BuyerServiceImpl implements BuyerService {

	private BuyerDAO buyerDAO = new BuyerDAOImpl(); 
	@Override
	public serviceResult createBuyer(BuyerVO buyer) {
		int cnt = buyerDAO.insertBuyer(buyer);
		serviceResult result = cnt >0? serviceResult.OK : serviceResult.FAIL;
		return result;
	}

	@Override
	public BuyerVO retrieveBuyer(String buyerId) throws PKNotFoundException {
		BuyerVO buyer = buyerDAO.selectBuyer(buyerId);
		if(buyer==null)
			throw new PKNotFoundException(MessageFormat.format("{0} 해당 거래처 없음 ", buyerId));
		return buyer;
	}

	@Override
	public List<BuyerVO> retrieveBuyerList() {
		return buyerDAO.selectBuyerList();
	}

	@Override
	public serviceResult modifyBuyer(BuyerVO buyer) {
		int cnt = buyerDAO.updateBuyer(buyer);
		serviceResult result = cnt>0? serviceResult.OK : serviceResult.FAIL;
		return result;
	}
	
	@Override
	public serviceResult removeBuyer(String buyerId) {
		int cnt = buyerDAO.deleteBuyer(buyerId);
		serviceResult result = cnt >0? serviceResult.OK : serviceResult.FAIL;
		return result;
	}

}
