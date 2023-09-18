package kr.or.ddit.props.dao;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;

import kr.or.ddit.mybatis.CustomSqlSessionFactoryBuilder;
import kr.or.ddit.vo.PropertyVO;

public class PropertyDaoImpl implements PropertyDAO {
	private SqlSessionFactory sqlSessionFactory // 마이바티스 의존하는 객채형성 코드 
				= CustomSqlSessionFactoryBuilder.getSqlSessionFactory(); 
	@Override
	public int insertProperty(PropertyVO prop) {
		try(
			SqlSession sqlSession = sqlSessionFactory.openSession(false);// openSession(false)이건 이제 커밋을 안해주겠따 이뜻은 이제 내가 커밋 해야한다. 제어권 내가 가질거야~~!
		){
			PropertyDAO mapperProxy = sqlSession.getMapper(PropertyDAO.class);//mapperproxy는 이 매퍼 인터페이스와 실제 sql문을 실행하는 코드 사이에서 동작한다. 매퍼인터페이스의 메서드 호출을 가로채서 
																				// mybatis내부에서 해당 sql문을 실행하고 그 결과를 반환한다. 
			int cnt= mapperProxy.insertProperty(prop);							// 이 매퍼 프록시를 생성하기 위해서 sqlsession객체를 사용하고 sqlsession은 getmapper메서드를 제공하고 이 메서드를 통해서 프록시를 생성한다. 
			sqlSession.commit();
			return cnt;
		}
		
	}

	@Override
	public PropertyVO selectProperty(String propertyName) {
		try(
			SqlSession sqlSession = sqlSessionFactory.openSession();
		){
			//return mapperProxy.selectProperty(propertyName);
			//return sqlSession.selectOne("kr.or.ddit.props.dao.PropertyDAO.selectProperty",propertyName);
			PropertyDAO mapperProxy = sqlSession.getMapper(PropertyDAO.class);
			return mapperProxy.selectProperty(propertyName);
		}
		
	}

	@Override
	public List<PropertyVO> selectProperties() {
		try(
			SqlSession sqlSession = sqlSessionFactory.openSession();
		){
			//return sqlSession.selectList("kr.or.ddit.props.dao.PropertyDAO.selectProperties");
			PropertyDAO mapperProxy = sqlSession.getMapper(PropertyDAO.class);
			return mapperProxy.selectProperties();
		}
	}

	@Override
	public int updateProperty(PropertyVO prop) {
		try(
			SqlSession sqlSession = sqlSessionFactory.openSession();
		){
			PropertyDAO mapperProxy = sqlSession.getMapper(PropertyDAO.class);
			return mapperProxy.updateProperty(prop);
		}
	}

//	TDD(Test Driven Development) : 테스트 주도용 개발 
	@Override
	public int deleteProperty(String propertyName) {
		try(
			SqlSession sqlSession = sqlSessionFactory.openSession(true);// 아무것도 안주면 오토커밋 false;
		){
			PropertyDAO mapperProxy = sqlSession.getMapper(PropertyDAO.class);
			return mapperProxy.deleteProperty(propertyName);
//			sqlSession.delete("", propertyName);
		}
		
		
	}

}
