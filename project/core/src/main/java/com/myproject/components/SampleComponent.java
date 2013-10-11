package com.myproject.components;

import com.adobe.consulting.components.AbstractComponent;

public class SampleComponent extends AbstractComponent {

	private String customTitle;
	
	@Override
	public void init() throws Exception {
		
		customTitle = "Telegraph : " + getCurrentPage().getTitle();
		
	}

	public String getCustomTitle() {
		return customTitle;
	}

}
