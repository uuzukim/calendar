package kr.or.ddit.prod.dao;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;

import kr.or.ddit.mybatis.CustomSqlSessionFactoryBuilder;
import kr.or.ddit.vo.BuyerVO;
import kr.or.ddit.vo.LprodVO;

public class OthersDaoImpl implements OthersDAO {

	private SqlSessionFactory sqlSessionFactory 
	= CustomSqlSessionFactoryBuilder.getSqlSessionFactory();
	
	@Override
	public List<LprodVO> selectLprodList() {
		try (
				SqlSession sqlSession = sqlSessionFactory.openSession();
			) {
				return sqlSession.getMapper(OthersDAO.class).selectLprodList();
			}
	}

	@Override
	public List<BuyerVO> selectBuyerList() {
		try (
				SqlSession sqlSession = sqlSessionFactory.openSession();
			) {
				return sqlSession.getMapper(OthersDAO.class).selectBuyerList();
			}
	}

}
