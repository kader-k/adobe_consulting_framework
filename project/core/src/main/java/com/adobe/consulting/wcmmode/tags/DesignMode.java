package com.adobe.consulting.wcmmode.tags;

import com.day.cq.wcm.api.WCMMode;

/**
 * Implementation of the &lt;wcmmode:design&gt; tag, everything inside this tag
 * will be executed when the <code>WcmMode</code> is <code>WCMMode.DESIGN</code>.
 * You can also specify the attribute <code>not=true</code>, then it will be executed
 * when the <code>WcmMode</code> is *not* <code>WCMMode.DESIGN</code>
 *
 * @see <a href="http://dev.day.com/docs/en/cq/current/javadoc/com/day/cq/wcm/api/WCMMode.html">WCMMode</a>
 */
public class DesignMode extends AbstractMode {

	private static final long serialVersionUID = 7829588826750738104L;

	@Override
	public WCMMode getMode() {
		return WCMMode.DESIGN;
	}
	
}
