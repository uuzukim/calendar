package kr.or.ddit.prop.dao;

import static org.junit.jupiter.api.Assertions.*;

import java.util.List;

import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;

import kr.or.ddit.props.dao.PropertyDAO;
import kr.or.ddit.props.dao.PropertyDAOImpl_FS;
import kr.or.ddit.vo.PropertyVO;

class PropertyDAOImpl_FSTest {

	private PropertyDAO dao = new PropertyDAOImpl_FS();
	
	@Test
	@Order(value = 1)
	void testInsertProperty() {
		PropertyVO prop=new PropertyVO();
		prop.setPropertyName("prop2");
		prop.setPropertyValue("prop value 2");
		int count = dao.insertProperty(prop);
		assertEquals(1, count); // 1은 예상값, count 결과값
	}

	@Test
	@Order(2)
	void testSelectProperties() {
		List<PropertyVO> propList = dao.selectProperties();
		assertNotNull(propList);
		propList.forEach(System.out::println);
	}

}
