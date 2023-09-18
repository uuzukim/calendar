package kr.or.ddit.mybatis;

import java.io.IOException;
import java.io.Reader;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import kr.or.ddit.exception.PersistenceException;

public class CustomSqlSessionFactoryBuilder {
	private static SqlSessionFactory sqlSessionFactory;
	static { // static 코드 블럭
		String configResource = "kr/or/ddit/db/mybatis/Configuration.xml";
		//inputstream에는 두가지가 있지? 바이트랑 캐릭터 근데 xml은 어차피 다 문자열이니까 캐릭터로 받아와도 된다~~~! 
		try(// reader는 읽고 닫혀야겠지? 그래서 try()안에 들어온다
			Reader reader= Resources.getResourceAsReader(configResource);
		) { 
			sqlSessionFactory = new SqlSessionFactoryBuilder().build(reader);
		} catch (IOException e) {
			throw new PersistenceException(e);
		}
	}
	
	public static SqlSessionFactory getSqlSessionFactory() {
		return sqlSessionFactory;
	}
	
}
