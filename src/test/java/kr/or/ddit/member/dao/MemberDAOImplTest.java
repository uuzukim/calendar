package kr.or.ddit.member.dao;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import kr.or.ddit.vo.MemberVO;

class MemberDAOImplTest {

	private MemberDAO dao = new MemberDAOImpl();

	@BeforeAll
	static void setUpBeforeClass() throws Exception {
	}

	@Test
	void testInsertMember() {
		fail("Not yet implemented");
	}

	@Test
	void testSelectMemberForAuth() {
		fail("Not yet implemented");
	}

	@Test
	void testSelectMember() {
		MemberVO member = dao.selectMember("b001");
		assertNotNull(member);
	}

	@Test
	void testSelectMemberList() {
		fail("Not yet implemented");
	}

	@Test
	void testUpdateMember() {
		fail("Not yet implemented");
	}

	@Test
	void testDeleteMember() {
		fail("Not yet implemented");
	}

}
