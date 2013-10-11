package com.myproject.services;

import org.apache.felix.scr.annotations.Component;
import org.apache.felix.scr.annotations.Service;

@Service(value=CookieService.class)
@Component
public class CookieService {
	
	protected void setSSOService(SSOService service) {
	}
	
}
