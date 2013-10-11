package com.adobe.consulting.components.tags;

import java.io.IOException;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.PageContext;
import javax.servlet.jsp.tagext.SimpleTagSupport;

import org.apache.sling.api.SlingHttpServletRequest;
import org.apache.sling.api.scripting.SlingBindings;
import org.apache.sling.api.scripting.SlingScriptHelper;
import org.apache.sling.commons.classloader.DynamicClassLoaderManager;
import org.apache.sling.scripting.jsp.util.TagUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.adobe.consulting.page.AbstractPage;
import com.day.cq.wcm.api.Page;
import com.day.cq.wcm.api.PageManager;

public class InitTag extends SimpleTagSupport {

    /**
     * the logger
     */
    private static final Logger log = LoggerFactory.getLogger(InitTag.class);
	
	private String id;
	
	private String pageObject;


	@Override
	public void doTag() throws JspException, IOException {
		
		SlingHttpServletRequest slingRequest = TagUtil.getRequest((PageContext)getJspContext());
		
		getJspContext().setAttribute(id, getCustomPage(slingRequest));
	}


	private AbstractPage getCustomPage(SlingHttpServletRequest slingRequest) {
		
		
		AbstractPage customPage = createCustomPageObject(slingRequest);

		if ( customPage != null) {
			PageManager pageManager = slingRequest.getResourceResolver().adaptTo(
					PageManager.class);
			Page currentPage = pageManager.getContainingPage(slingRequest.getResource());
			
			customPage.setPage(currentPage);
		}
		return customPage;
	
	
	}
	
	private AbstractPage createCustomPageObject(SlingHttpServletRequest slingRequest) {
		try{
			return createCustomPage(getSlingScriptHelper(slingRequest));
		} catch (Exception e) {
			log.error("Error during creating of object {}", getPageObject());
			log.error("Error curing createCustomPageObject", e);
		}
		return null;
	}
	
	private SlingScriptHelper getSlingScriptHelper(SlingHttpServletRequest slingRequest) {
        SlingBindings bindings = (SlingBindings) slingRequest.getAttribute(SlingBindings.class.getName());
        return bindings.getSling();
	}
	

    @SuppressWarnings("rawtypes")
	private AbstractPage createCustomPage(SlingScriptHelper sling) throws ClassNotFoundException, IllegalAccessException, InstantiationException{
        Class targetClass = null;
        if (sling != null) {
            DynamicClassLoaderManager dclm = sling.getService(DynamicClassLoaderManager.class);
            if (dclm != null) {
                targetClass = dclm.getDynamicClassLoader().loadClass(this.getPageObject());
            }
        }
        if (targetClass == null) targetClass = Class.forName(this.getPageObject());
        if (targetClass == null) return null;
        return (AbstractPage) targetClass.newInstance();
    }


	public String getId() {
		return id;
	}


	public void setId(String id) {
		this.id = id;
	}


	public String getPageObject() {
		return pageObject;
	}


	public void setPageObject(String pageObject) {
		this.pageObject = pageObject;
	}
	
	
}
