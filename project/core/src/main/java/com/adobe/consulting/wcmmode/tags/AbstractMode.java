package com.adobe.consulting.wcmmode.tags;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.jsp.JspException;
import javax.servlet.jsp.tagext.TagSupport;

import com.day.cq.wcm.api.WCMMode;

/**
 * Abstract class used to implement the other tags for the WcmMode.
 * The WcmMode tags can be used in your JSP to show/hide particular bits
 * for a certain WcmMode. 
 *
 */
public abstract class AbstractMode extends TagSupport {

	private static final long serialVersionUID = -5908186805353457797L;

	private Boolean not = false;

	@Override
    public int doStartTag() throws JspException {
    	
    	if ( ! isRequestInMode()) {
    		return SKIP_BODY;
    	}

    	return EVAL_BODY_INCLUDE;
    }

    @Override
    public int doEndTag() throws JspException {
        return EVAL_PAGE;
    }
    
	public void setNot(Boolean not) {
		this.not = not;
	}

    
    public abstract WCMMode getMode();
    
    private boolean isRequestInMode() {
        HttpServletRequest request = (HttpServletRequest) pageContext.getRequest();
        
        if ( not ) {
        	return ( WCMMode.fromRequest(request) != getMode() );
        }

    	return ( WCMMode.fromRequest(request) == getMode() );
    }

}
