package com.adobe.consulting.components;

import org.apache.sling.api.resource.Resource;
import org.apache.sling.api.resource.ResourceWrapper;

public abstract class NewComponent extends ResourceWrapper {

	public NewComponent(Resource resource) {
		super(resource);
	}
	
}
