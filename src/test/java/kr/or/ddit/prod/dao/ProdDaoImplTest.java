package kr.or.ddit.prod.dao;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import kr.or.ddit.vo.ProdVO;

class ProdDaoImplTest {
	private ProdDAO dao = new ProdDaoImpl();
	
	@BeforeAll
	static void setUpBeforeClass() throws Exception {
	}

	@Test
	void testInsertProd() {
		fail("Not yet implemented");
	}

	@Test
	void testSelectProd() {
		ProdVO prod = dao.selectProd("P101000001");
		assertNotNull(prod);
	}

	@Test
	void testSelectProdList() {
		fail("Not yet implemented");
	}

	@Test
	void testUpdateProd() {
		fail("Not yet implemented");
	}

}
