package com.myproject.page;

import java.util.Iterator;

import com.adobe.consulting.page.AbstractPage;
import com.day.cq.wcm.api.Page;
import com.day.cq.wcm.api.PageFilter;

public class MyPage extends AbstractPage {
	
	public Iterator<Page> getChildren() {
		return page.listChildren(new PageFilter());
	}
	
	public boolean isHomepage() {
		return page.getDepth() == 2;
	}
	
}
