package com.adobe.consulting.components.tags;


import com.adobe.consulting.components.AbstractComponent;
import com.day.cq.wcm.api.Page;
import com.day.cq.wcm.api.PageManager;
import com.day.cq.wcm.api.components.ComponentContext;
import com.day.cq.wcm.api.designer.Design;
import com.day.cq.wcm.api.designer.Designer;
import com.day.cq.wcm.commons.WCMUtils;
import org.apache.sling.api.SlingHttpServletRequest;
import org.apache.sling.api.SlingHttpServletResponse;
import org.apache.sling.api.resource.ValueMap;
import org.apache.sling.api.resource.ResourceUtil;
import org.apache.sling.api.resource.Resource;
import org.apache.sling.api.resource.ResourceResolver;
import org.apache.sling.api.scripting.SlingBindings;
import org.apache.sling.api.scripting.SlingScriptHelper;
import org.apache.sling.commons.classloader.DynamicClassLoaderManager;
import org.apache.sling.scripting.jsp.util.TagUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


import javax.servlet.jsp.JspException;
import javax.servlet.jsp.PageContext;
import javax.servlet.jsp.tagext.SimpleTagSupport;


/**
 * The action tag is used to implement the presenter pattern.<br/>
 * In your JSP you can add the following tag <br/>
 * &lt;cmp:presenter className="yourpackage.YourClassName" id="yourId"/&gt;
 * 
 * The class must extend this <code>AbstractComponent</code>.
 * @see AbstractComponent
 */
public class ActionTag extends SimpleTagSupport {
    /**
     * the logger
     */
    private static final Logger log = LoggerFactory.getLogger(ActionTag.class);
    private String className;
    private String id;

    public ActionTag() {
    }

    /**
     * load all objects from /libs/wcm/global.jsp
     */
    private void initialize() {
        try {
            PageContext pageContext = (PageContext) getJspContext();

            SlingHttpServletRequest slingRequest = TagUtil.getRequest(pageContext);
            SlingHttpServletResponse slingResponse = TagUtil.getResponse(pageContext);

            Resource resource = slingRequest.getResource();
            ResourceResolver resourceResolver = slingRequest.getResourceResolver();
            PageManager pageManager = resourceResolver.adaptTo(PageManager.class);
            Designer designer = resourceResolver.adaptTo(Designer.class);
            Page currentPage = pageManager.getContainingPage(resource);
            Design currentDesign = designer.getDesign(currentPage);
            ComponentContext componentContext = WCMUtils.getComponentContext(slingRequest);

            final SlingBindings bindings = (SlingBindings) slingRequest.getAttribute(SlingBindings.class.getName());
            final SlingScriptHelper scriptHelper = bindings.getSling();

            // Initialize Bean
            AbstractComponent bean = getComponent(scriptHelper);
            

            // set common page objects
            bean.setProperties(ResourceUtil.getValueMap(resource));
            bean.setPageManager(pageManager);
            bean.setScriptHelper(scriptHelper);

            bean.setCurrentPage(currentPage);

            bean.setPageProperties(currentPage == null ? ValueMap.EMPTY : currentPage.getProperties());

            bean.setDesigner(designer);

            bean.setCurrentDesign(currentDesign);

            bean.setCurrentStyle(currentDesign == null || componentContext == null
                    ? null
                    : currentDesign.getStyle(componentContext.getCell()));

            bean.setSlingRequest(slingRequest);

            bean.setSlingResponse(slingResponse);

            bean.setPageContext(pageContext);

            bean.setComponentContext(componentContext);

            bean.setResourceResolver(resourceResolver);

            // initialize the presenter
            bean.init();

            // add bean to pagecontext
            pageContext.setAttribute(id, bean);

        } catch (InstantiationException | IllegalAccessException | ClassNotFoundException e) {
            log.error("Couldn't initialize BeanTag", e);
        } catch (Exception e) {
            log.error("Unkown Exception", e);
        }

    }

    @Override
    public void doTag() throws JspException {
        try {
            initialize();
        } catch (Exception e) {
            log.error("Unkown Exception", e);
        }
    }

    public void setClassName(String className) {
        this.className = className;
    }

    public void setId(String id) {
        this.id = id;
    }

    @SuppressWarnings("rawtypes")
	private AbstractComponent getComponent(SlingScriptHelper sling) throws ClassNotFoundException, IllegalAccessException, InstantiationException{
        Class targetClass = null;
        if (sling != null) {
            DynamicClassLoaderManager dclm = sling.getService(DynamicClassLoaderManager.class);
            if (dclm != null) {
                targetClass = dclm.getDynamicClassLoader().loadClass(this.className);
            }
        }
        if (targetClass == null) targetClass = Class.forName(this.className);
        if (targetClass == null) return null;
        return (AbstractComponent) targetClass.newInstance();
    }

}
