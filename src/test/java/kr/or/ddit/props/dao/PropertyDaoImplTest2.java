package kr.or.ddit.props.dao;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import kr.or.ddit.vo.PropertyVO;

class PropertyDaoImplTest2 {

	private PropertyDAO dao = new PropertyDaoImpl();
	
	@Test
	void testInsertProperty() {
		PropertyVO prop = new PropertyVO();
		prop.setPropertyName("nonCommitPN");
		prop.setPropertyValue("nonCommitPV");
//		prop.setDescription("nonCommitDS");
		int cnt = dao.insertProperty(prop);
		assertEquals(1, cnt);
	}

	@Test
	void testSelectProperty() {
		PropertyVO prop = dao.selectProperty("nonCommitPN");
		assertNotNull(prop);
	}

	@Test
	void testSelectProperties() {
		dao.selectProperties().forEach(System.out::println); // selectProperties()에서 가져온 값을 하나씩 반복해서 출력하라는뜻
	}

	@Test
	void testUpdateProperty() {
		PropertyVO prop = new PropertyVO();
		prop.setPropertyName("Hi");
		prop.setPropertyValue("Bye");
		prop.setDescription("What?");
		int cnt = dao.updateProperty(prop);
		assertEquals(1, cnt);
	}

	@Test
	void testDeleteProperty() {
		int cnt = dao.deleteProperty("nonCommitPN");
		assertNotEquals(0, cnt);
	}

}
