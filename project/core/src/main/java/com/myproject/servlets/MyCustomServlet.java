package com.myproject.servlets;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Iterator;

import javax.jcr.Node;
import javax.jcr.RepositoryException;
import javax.servlet.ServletException;

import org.apache.felix.scr.annotations.Activate;
import org.apache.felix.scr.annotations.sling.SlingServlet;
import org.apache.sling.api.SlingHttpServletRequest;
import org.apache.sling.api.SlingHttpServletResponse;
import org.apache.sling.api.resource.Resource;
import org.apache.sling.api.resource.ResourceUtil;
import org.apache.sling.api.resource.ValueMap;
import org.apache.sling.api.servlets.SlingAllMethodsServlet;
import org.apache.sling.commons.osgi.PropertiesUtil;
import org.osgi.service.component.ComponentContext;

import com.day.cq.wcm.api.Page;
import com.day.cq.wcm.api.PageManager;

@SlingServlet(paths = {"/bin/formsRedirect.html"}, methods = "GET,POST", metatype=true)
public class MyCustomServlet extends SlingAllMethodsServlet {

	private static final long serialVersionUID = 1348950519419964128L;
	
	
	@Activate
	protected void activate(ComponentContext context) {
		PropertiesUtil.toBoolean(context.getProperties().get("myproperty"), false);
	}
	
	@Override
	protected void doPost(SlingHttpServletRequest request,
			SlingHttpServletResponse response) throws ServletException,
			IOException {
		doGet(request,response);
	}


	@Override
	protected void doGet(SlingHttpServletRequest request,
			SlingHttpServletResponse response) throws ServletException,
			IOException {

		
		String path = request.getParameter("path");
		
		Iterator<Resource> result = request.getResourceResolver().findResources("/jcr:root/content/usergenerated//*[jcr:contains(., '"+path+"')] order by @jcr:score", "xpath");
		
		Resource answers = result.next();
		
		Node answerNode = answers.getParent().adaptTo(Node.class);
		String formPath = "";
		try {
			formPath = answerNode.getProperty("formPath").getString();
		} catch (RepositoryException e) {
		}
		
		ValueMap props = ResourceUtil.getValueMap(answers);
		
		PageManager pm = request.getResourceResolver().adaptTo(PageManager.class);
		Page p = pm.getContainingPage(request.getResourceResolver().resolve(formPath));
		

		PrintWriter pw = response.getWriter();
		
		pw.println("Page path : " + p.getPath());
		pw.println("<br/>");
		
		Iterator<String> propNames = props.keySet().iterator();
		while ( propNames.hasNext()) {
			String propName = propNames.next();
			pw.println("Name : ");
			pw.println(propName);
			pw.println(" value : ");
			pw.println(props.get(propName, String.class));
			pw.println("<br/>");
		}
		
		pw.close();
		pw.flush();
	}

}
