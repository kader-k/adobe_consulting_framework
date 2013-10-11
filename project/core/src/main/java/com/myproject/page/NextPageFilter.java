package com.myproject.page;

import com.day.cq.commons.Filter;
import com.day.cq.wcm.api.Page;

public class NextPageFilter implements Filter<Page> {

	String pageName;
	String previousPageName;
	boolean pageFound = false;
	
	public NextPageFilter(String pageName ) {
		this.pageName = pageName;
	}
	
	
	@Override
	public boolean includes(Page nextPage) {
		
		if (pageFound) {
			pageFound = false;
			return true;
		}
		
	    if(nextPage.getName().equals(pageName)){
	    	pageFound = true;
	    }
	    return false;
	}

}
