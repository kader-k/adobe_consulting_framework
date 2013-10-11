package com.myproject.components;

import org.apache.sling.api.resource.Resource;

import com.adobe.consulting.components.AbstractComponent;
import com.day.cq.commons.RangeIterator;
import com.day.cq.tagging.TagManager;

public class SmartListComponent extends AbstractComponent {

	private RangeIterator<Resource> docs;
	
	public RangeIterator<Resource> getDocs() {
		return docs;
	}

	@Override
	public void init() throws Exception {
		
		TagManager tm = getResourceResolver().adaptTo(TagManager.class);
		
		if ( getCurrentPage().getTags().length > 0 ) {
			docs = tm.find("/content/st/en/features", new String[]{getCurrentPage().getTags()[0].getTagID()});
		}
		
	}

}
