package kr.or.ddit.mvc;

import java.io.IOException;
import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.tiles.definition.NoSuchDefinitionException;

import lombok.extern.slf4j.Slf4j;

// 여기서는 나머지 viewresolver를 조합해야한다. 
@Slf4j
public class ViewResolverComposite implements ViewResolver {
	private List<ViewResolver>viewResolvers;
	
	public ViewResolverComposite(){
		viewResolvers = new ArrayList<>();
		TilesViewResolver resolver1 = new TilesViewResolver();
		viewResolvers.add(resolver1);

		InternalResouceViewResolver resolver2 = new InternalResouceViewResolver();
		resolver2.setPrefix("/WEB-INF/views/");
		resolver2.setSuffix(".jsp");
		viewResolvers.add(resolver2);
	}
	
	@Override
	public void resolveView(String logicalViewName, HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {

		for(ViewResolver resolver : viewResolvers ) {
			try {
				resolver.resolveView(logicalViewName, req, resp);
				break;
			}catch(NoSuchDefinitionException e) {
				log.warn(MessageFormat.format("{0}가 {1}를 해결하지 못했음.", resolver.getClass().getName(), logicalViewName), e);
				
				
				continue;
			}
		}
	}

}
