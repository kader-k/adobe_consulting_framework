package com.myproject.components;

import java.util.ArrayList;
import java.util.List;

import com.adobe.consulting.components.AbstractComponent;
import com.day.cq.wcm.api.Page;

public class BreadcrumpComponent extends AbstractComponent {

	private List<Page> pages;
	
	@Override
	public void init() throws Exception {
		
		pages = new ArrayList<>();
		
		Page page = getCurrentPage();
		
		while (page.getDepth() >= 4 ) {
			pages.add(0, page);
			page = page.getParent();
		}
		
	}

	public List<Page> getPages() {
		return pages;
	}


}
