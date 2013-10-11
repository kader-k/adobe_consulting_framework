package com.myproject.listeners;

import javax.jcr.observation.EventIterator;
import javax.jcr.observation.EventListener;

import org.apache.felix.scr.annotations.Activate;
import org.apache.felix.scr.annotations.Component;
import org.osgi.service.component.ComponentContext;

@Component(metatype = true, immediate = true)
public class MyCustomEventListener implements EventListener {

	@Override
	public void onEvent(EventIterator arg0) {
		// TODO Auto-generated method stub
		
	}

	   @Activate
	   public void activate(ComponentContext context) {
	   }

	
	
}
