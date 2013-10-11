package com.adobe.consulting.wcmmode.tags;

import com.day.cq.wcm.api.WCMMode;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.jsp.JspException;
import javax.servlet.jsp.tagext.TagSupport;

/**
 * Implementation of the &lt;wcmmode:setMode&gt; tag, this sets the WcmMode to 
 * the specified more and restores it to the original mode.<br/>
 * The following attributes can be specified:
 * <ul>
 * <li>mode: to mode to be set</li>
 * <li>restore: must the original mode be restored (default true)</li>
 * </ul>
 * Example:<br/>
 * &lt;wcmmode:setMode mode="disabled"&gt;
 * ...
 * &lt;/wcmmode:setMode&gt;
 *
 * @see <a href="http://dev.day.com/docs/en/cq/current/javadoc/com/day/cq/wcm/api/WCMMode.html">WCMMode</a>
 */
public class SetWcmMode extends TagSupport {

	private static final long serialVersionUID = 1247938294323013878L;

	private static final Logger log = LoggerFactory.getLogger(SetWcmMode.class);

    private String mode;

    private Boolean restore = true;

    private WCMMode oldMode = null;

    @Override
    public int doStartTag() throws JspException {
        WCMMode toSet = null;
        if (WCMMode.DESIGN.name().equalsIgnoreCase(mode)) {
            toSet = WCMMode.DESIGN;
        } else if (WCMMode.DISABLED.name().equalsIgnoreCase(mode)) {
            toSet = WCMMode.DISABLED;
        } else if (WCMMode.EDIT.name().equalsIgnoreCase(mode)) {
            toSet = WCMMode.EDIT;
        } else if (WCMMode.PREVIEW.name().equalsIgnoreCase(mode)) {
            toSet = WCMMode.PREVIEW;
        } else if (WCMMode.READ_ONLY.name().equalsIgnoreCase(mode)) {
            toSet = WCMMode.READ_ONLY;
        } 
        try {
            if (toSet != null) {
                HttpServletRequest request = (HttpServletRequest) pageContext.getRequest();
                this.oldMode = WCMMode.fromRequest(request);
                toSet.toRequest(request);
                log.debug("Setting WCMMode: "+toSet.name());
            }
        } catch (Exception e) {
            log.error("Unkown Exception", e);
        }
        return EVAL_BODY_INCLUDE;
    }

    @Override
    public int doEndTag() throws JspException {
        if (restore && oldMode != null) {
            HttpServletRequest request = (HttpServletRequest) pageContext.getRequest();
            oldMode.toRequest(request);
            log.debug("Restoring old WCMMode: "+oldMode.name());
        }
        return EVAL_PAGE;
    }

    public void setMode(String mode) {
        this.mode = mode;
    }

    public void setRestore(Boolean restore) {
        this.restore = restore;
    }

}
