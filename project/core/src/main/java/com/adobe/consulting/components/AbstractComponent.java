package com.adobe.consulting.components;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.jsp.PageContext;

import org.apache.sling.api.SlingHttpServletRequest;
import org.apache.sling.api.SlingHttpServletResponse;
import org.apache.sling.api.resource.ResourceResolver;
import org.apache.sling.api.resource.ValueMap;
import org.apache.sling.api.scripting.SlingScriptHelper;

import com.day.cq.wcm.api.Page;
import com.day.cq.wcm.api.PageManager;
import com.day.cq.wcm.api.components.ComponentContext;
import com.day.cq.wcm.api.components.EditContext;
import com.day.cq.wcm.api.designer.Design;
import com.day.cq.wcm.api.designer.Designer;
import com.day.cq.wcm.api.designer.Style;

/**
 * Base class to implement the presenter pattern to move
 * the logic from the JSP/Component into a Java-class.<br/>
 * In your JSP you can add the following tag <br/>
 * &lt;cmp:presenter className="yourpackage.YourClassName" id="yourId"/&gt;
 * 
 * The class must extend this <code>AbstractComponent</code>.
 * In the <code>init</code> method you have access to all objects you would have in the JSP.
 *
 */
public abstract class AbstractComponent {

	private EditContext editContext;
	private ValueMap properties;
	private PageManager pageManager;
	private Page currentPage;
	private ValueMap pageProperties;
	private Designer designer;
	private Design currentDesign;
	private Style currentStyle;
	private SlingHttpServletRequest slingRequest;
	private SlingHttpServletResponse slingResponse;
	private PageContext pageContext;
	private ComponentContext componentContext;
	private ResourceResolver resourceResolver;
	private SlingScriptHelper scriptHelper;

	/**
	 * @return the request
	 */
	public SlingHttpServletRequest getSlingRequest() {
		return slingRequest;
	}

	/**
	 * @param slingRequest
	 *            SlingHttpServletRequest
	 */
	public void setSlingRequest(SlingHttpServletRequest slingRequest) {
		this.slingRequest = slingRequest;
	}

	/**
	 * @return the response
	 */
	public SlingHttpServletResponse getSlingResponse() {
		return slingResponse;
	}

	/**
	 * @param _slingResponse
	 *            SlingHttpServletResponse the response to set
	 */
	public void setSlingResponse(SlingHttpServletResponse _slingResponse) {
		this.slingResponse = _slingResponse;
	}

	/**
	 * @return the request
	 */
	public HttpServletRequest getRequest() {
		return slingRequest;
	}

	/**
	 * @return the response
	 */
	public HttpServletResponse getResponse() {
		return slingResponse;
	}

	public abstract void init() throws Exception;

	/**
	 * @return the editContext
	 */
	public EditContext getEditContext() {
		return editContext;
	}

	/**
	 * @param editContext
	 *            the editContext to set
	 */
	public void setEditContext(EditContext editContext) {
		this.editContext = editContext;
	}

	/**
	 * @return the properties
	 */
	public ValueMap getProperties() {
		if (properties == null) {
			return ValueMap.EMPTY;
		}
		return properties;
	}

	/**
	 * @param properties
	 *            the properties to set
	 */
	public void setProperties(ValueMap properties) {
		this.properties = properties;
	}

	/**
	 * @return the pageManager
	 */
	public PageManager getPageManager() {
		return pageManager;
	}

	/**
	 * @param pageManager
	 *            the pageManager to set
	 */
	public void setPageManager(PageManager pageManager) {
		this.pageManager = pageManager;
	}

	/**
	 * @return the currentPage
	 */
	public Page getCurrentPage() {
		return currentPage;
	}

	/**
	 * @param currentPage
	 *            the currentPage to set
	 */
	public void setCurrentPage(Page currentPage) {
		this.currentPage = currentPage;
	}

	/**
	 * @return the pageProperties
	 */
	public ValueMap getPageProperties() {
		return pageProperties;
	}

	/**
	 * @param pageProperties
	 *            the pageProperties to set
	 */
	public void setPageProperties(ValueMap pageProperties) {
		this.pageProperties = pageProperties;
	}

	/**
	 * @return the designer
	 */
	public Designer getDesigner() {
		return designer;
	}

	/**
	 * @param designer
	 *            the designer to set
	 */
	public void setDesigner(Designer designer) {
		this.designer = designer;
	}

	/**
	 * @return the currentDesign
	 */
	public Design getCurrentDesign() {
		return currentDesign;
	}

	/**
	 * @param currentDesign
	 *            the currentDesign to set
	 */
	public void setCurrentDesign(Design currentDesign) {
		this.currentDesign = currentDesign;
	}

	/**
	 * @return the currentStyle
	 */
	public Style getCurrentStyle() {
		return currentStyle;
	}

	/**
	 * @param currentStyle
	 *            the currentStyle to set
	 */
	public void setCurrentStyle(Style currentStyle) {
		this.currentStyle = currentStyle;
	}

	/**
	 * 
	 * @param pageContext
	 */
	public void setPageContext(PageContext pageContext) {
		this.pageContext = pageContext;
	}

	/**
	 * 
	 * @return
	 */
	public PageContext getPageContext() {
		return this.pageContext;
	}

	/**
	 * 
	 * @param componentContext
	 */
	public void setComponentContext(ComponentContext componentContext) {
		this.componentContext = componentContext;
	}

	/**
	 * 
	 * @return
	 */
	public ComponentContext getComponentContext() {
		return this.componentContext;
	}

	public void setResourceResolver(ResourceResolver resourceResolver) {
		this.resourceResolver = resourceResolver;
	}

	public ResourceResolver getResourceResolver() {
		return this.resourceResolver;
	}

	public SlingScriptHelper getScriptHelper() {
		return scriptHelper;
	}

	public void setScriptHelper(SlingScriptHelper scriptHelper) {
		this.scriptHelper = scriptHelper;
	}

}