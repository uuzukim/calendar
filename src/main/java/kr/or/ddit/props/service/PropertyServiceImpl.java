package kr.or.ddit.props.service;

import java.text.MessageFormat;
import java.util.List;

import kr.or.ddit.exception.PKNotFoundException;
import kr.or.ddit.props.dao.PropertyDAO;
import kr.or.ddit.props.dao.PropertyDaoImpl;
import kr.or.ddit.vo.PropertyVO;

public class PropertyServiceImpl implements PropertyService{
	private PropertyDAO dao = new PropertyDaoImpl();
	
	public boolean createProperty(PropertyVO prop){
		 int count = dao.insertProperty(prop);
		 return count>0;
	}

	@Override
	public PropertyVO retrieveProperty(String propertyName) {
		PropertyVO prop = dao.selectProperty(propertyName);
		if(prop==null)
			throw new PKNotFoundException(MessageFormat.format("{0}에 해당하는 데이터 없음.",propertyName));
		return prop;
	}

	@Override
	public List<PropertyVO> retrieveProperties() {

		return dao.selectProperties();
	}

	@Override
	public boolean modiProperty(PropertyVO prop) {
		int cnt = dao.updateProperty(prop);
			boolean count =false;
			if(cnt>0 ) {
				count = true;
			}
		return count;
	}

	@Override
	public boolean removeProperty(String propertyName) {
		int count = dao.deleteProperty(propertyName);
		return count > 0;
	}
}
