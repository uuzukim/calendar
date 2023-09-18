package kr.or.ddit.props.service;

import java.util.List;

import kr.or.ddit.vo.PropertyVO;

/**
 * Property 관리를 위한 Business Logic Layer 
 *
 */
public interface PropertyService {
	public boolean createProperty(PropertyVO prop); //insert
	public PropertyVO retrieveProperty(String propertyName); // selectone
	public List<PropertyVO> retrieveProperties(); //selectall
	public boolean modiProperty(PropertyVO prop); //update
	public boolean removeProperty(String propertyName);// delete
	
	
}
