package kr.or.ddit.props.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import kr.or.ddit.vo.PropertyVO;

/**
 *	Property 관리(CRUD)를 위한 Persistence Layer 
 *	
 */
public interface PropertyDAO {
	public int insertProperty(PropertyVO prop);
	public PropertyVO selectProperty(String propertyName);
	public List<PropertyVO> selectProperties();
	public int updateProperty(PropertyVO prop);
	public int deleteProperty(@Param("pn") String propertyName);// 이렇게 하면 map처럼 key가 pn, value가 propertyName이 된다. 
	
}
