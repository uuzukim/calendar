package kr.or.ddit.member.dao;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;

import kr.or.ddit.mybatis.CustomSqlSessionFactoryBuilder;
import kr.or.ddit.vo.MemberVO;

public class MemberDAOImpl implements MemberDAO {
	private SqlSessionFactory sqlSessionFactory 
				= CustomSqlSessionFactoryBuilder.getSqlSessionFactory();

	@Override
	public int insertMember(MemberVO member) {
		try(SqlSession sqlSession = sqlSessionFactory.openSession()){
			MemberDAO mapperProxy = sqlSession.getMapper(MemberDAO.class);
			int cnt = mapperProxy.insertMember(member);
			sqlSession.commit();
			return cnt;
		}
	}

	@Override
	public MemberVO selectMemberForAuth(String memId) {
		try (SqlSession sqlSession = sqlSessionFactory.openSession();) {
			MemberDAO mapperProxy = sqlSession.getMapper(MemberDAO.class);
			return mapperProxy.selectMemberForAuth(memId);
		}
	}

	@Override
	public MemberVO selectMember(String memId) {
		try (SqlSession sqlSession = sqlSessionFactory.openSession();) {
			MemberDAO mapperProxy = sqlSession.getMapper(MemberDAO.class);
			return mapperProxy.selectMember(memId);
		}
	}

	@Override
	public List<MemberVO> selectMemberList() {
		try (SqlSession sqlSession = sqlSessionFactory.openSession();) {
			MemberDAO mapperProxy = sqlSession.getMapper(MemberDAO.class);
			return mapperProxy.selectMemberList();
		}
	}

	@Override
	public int updateMember(MemberVO member) {
		try(SqlSession sqlSession = sqlSessionFactory.openSession()){
			MemberDAO mapperProxy = sqlSession.getMapper(MemberDAO.class);
			int cnt = mapperProxy.updateMember(member);
			sqlSession.commit();
			return cnt;
		}
	}

	@Override
	public int deleteMember(String memId) {
		try(SqlSession sqlSession = sqlSessionFactory.openSession()){
			MemberDAO mapperProxy = sqlSession.getMapper(MemberDAO.class);
			int cnt = mapperProxy.deleteMember(memId);
			sqlSession.commit();
			return cnt;
		}
	}
}
