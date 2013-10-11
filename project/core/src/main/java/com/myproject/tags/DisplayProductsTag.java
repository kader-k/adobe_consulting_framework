package com.myproject.tags;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.tagext.BodyTagSupport;

import org.apache.commons.lang.StringUtils;
import org.apache.sling.scripting.jsp.util.TagUtil;

import com.day.cq.wcm.api.WCMMode;

public class DisplayProductsTag extends BodyTagSupport {

	
	/**
	 * 
	 */
	private static final long serialVersionUID = 5136682352837250482L;

	@Override
	public int doAfterBody() throws JspException {

		String body = getBodyContent().getString();
		JspWriter out = getBodyContent().getEnclosingWriter();
		
		String output = null;
		
		WCMMode wcmMode = WCMMode.fromRequest(TagUtil.getRequest(pageContext));
		
		if ( wcmMode != WCMMode.EDIT && body != null && body.indexOf("{prod") != -1) {
			
			output = StringUtils.replace(body, "{prod.12345}", "avg one year NL");
			output = StringUtils.replace(output, "{prod.56789}", "avg one year CZ");
		}

		try {
			if ( output != null ) {
				out.print( output );
			} else {
				out.print( body );
			}
			
		} catch (Exception e ) {
			e.printStackTrace();
		}
		return SKIP_BODY;
	}
	
	
}
