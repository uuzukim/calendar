package kr.or.ddit.mvc.simple;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.ResourceBundle;

import javax.servlet.http.HttpServletRequest;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class AbstractControllerHandlerMapping implements HandlerMapping {
	private Map<String, AbstractController> handlerMap;

	public AbstractControllerHandlerMapping(String location) {
		handlerMap = new LinkedHashMap<>();
		ResourceBundle bundle = ResourceBundle.getBundle(location);
		bundle.keySet().forEach(key->{ // 여기서 key는 uri
			String qualifiedName = bundle.getString(key);
//			A a = new A(); 
//			relfection
			try {
				Class<? extends AbstractController> handlerClz = 
						(Class<? extends AbstractController>) Class.forName(qualifiedName);
				 AbstractController handlerInstance = handlerClz.newInstance();
				 handlerMap.put(key,handlerInstance);
				 log.info("{} : {} ",key, handlerClz.getName());
				 
			} catch (Exception e) {
//				throw new RuntimeException("command handler instance 생성 도중 문제 발생",e);
				log.error("command handler instance 생성 도중 문제 발생",e);
			}
			
		});
	}
	@Override
	public AbstractController findCommandHandler(HttpServletRequest req) {
		String requestURI = req.getRequestURI();
		String uri = requestURI.substring(req.getContextPath().length());
		return handlerMap.get(uri);
	}
}
