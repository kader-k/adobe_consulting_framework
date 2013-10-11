package com.adobe.consulting.wcmmode.tags;

import com.day.cq.wcm.api.WCMMode;

/**
 * Implementation of the &lt;wcmmode:disabled&gt; tag, everything inside this tag
 * will be executed when the <code>WcmMode</code> is <code>WCMMode.DISABLED</code>.
 * You can also specify the attribute <code>not=true</code>, then it will be executed
 * when the <code>WcmMode</code> is *not* <code>WCMMode.DISABLED</code>
 *
 * @see <a href="http://dev.day.com/docs/en/cq/current/javadoc/com/day/cq/wcm/api/WCMMode.html">WCMMode</a>
 */
public class DisabledMode extends AbstractMode {

	private static final long serialVersionUID = -6562052427982138483L;

	@Override
	public WCMMode getMode() {
		return WCMMode.DISABLED;
	}
	
}
