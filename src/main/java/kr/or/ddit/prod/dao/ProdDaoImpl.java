package kr.or.ddit.prod.dao;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;

import kr.or.ddit.member.dao.MemberDAO;
import kr.or.ddit.mybatis.CustomSqlSessionFactoryBuilder;
import kr.or.ddit.vo.ProdVO;

public class ProdDaoImpl implements ProdDAO {
	
	private SqlSessionFactory sqlSessionFactory 
								= CustomSqlSessionFactoryBuilder.getSqlSessionFactory();
	
	@Override
	public int insertProd(ProdVO prod) {
		try(SqlSession sqlSession = sqlSessionFactory.openSession()){
			ProdDAO mapperProxy = sqlSession.getMapper(ProdDAO.class);
			int cnt = mapperProxy.insertProd(prod);
			sqlSession.commit();
			return cnt;
		}
	}

	@Override
	public ProdVO selectProd(String prodId) {
		try (
				SqlSession sqlSession = sqlSessionFactory.openSession();
			) {
				ProdDAO mapperProxy = sqlSession.getMapper(ProdDAO.class);
				return mapperProxy.selectProd(prodId);
			}
	}

	@Override
	public List<ProdVO> selectProdList() {
		try (
			SqlSession sqlSession = sqlSessionFactory.openSession();
		) {
			ProdDAO mapperProxy = sqlSession.getMapper(ProdDAO.class);
			return mapperProxy.selectProdList();
		}
	}

	@Override
	public int updateProd(ProdVO prod) {
		try (
				SqlSession sqlSession = sqlSessionFactory.openSession();
			) {
				ProdDAO mapperProxy = sqlSession.getMapper(ProdDAO.class);
				int cnt = mapperProxy.updateProd(prod);
				sqlSession.commit(); // TCL(commit/rollback)
				return cnt;
		}
	}

}
