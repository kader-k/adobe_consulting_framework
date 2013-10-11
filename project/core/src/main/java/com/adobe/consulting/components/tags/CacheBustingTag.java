package com.adobe.consulting.components.tags;

import java.util.StringTokenizer;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.tagext.BodyTagSupport;

import org.apache.commons.lang.StringUtils;
import org.apache.felix.scr.annotations.Component;
import org.apache.felix.scr.annotations.Property;
import org.apache.felix.scr.annotations.Service;
import org.apache.sling.commons.osgi.PropertiesUtil;
import org.osgi.service.component.ComponentContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


@Component(label = "Cache busting tag", name = "CacheBusting", description = "Service to implement cache busting on top of the clientlibs functionality.", metatype = true, immediate = true)
@Service
public class CacheBustingTag extends BodyTagSupport {

	private static final Logger log = LoggerFactory.getLogger(CacheBustingTag.class);

	private static final long serialVersionUID = -1669700174823704010L;
	
	private static final String FILE_SEPARATOR = ".";
	
	private static final String HREF = "href=";
	
	private static final String SRC = "src=";
	
	private static final String[] FILE_REFERENCES = new String[] { HREF, SRC };
	
	private static final String STYLE_INLINE = "inline";
	
	private static final String STYLE_PARAMETER = "parameter";
	
	@Property(boolValue = true, label = "Enabled?", description = "Enable cache busting only on publish instance with a dispatcher in front of them.")
	private static final String PROP_ENABLED = "cachebusting.prop.enabled";

	private static boolean cacheBustingEnabled = true;
	
	private String output;
	
	private String style;
	
	private String uniqueId;
	
	
	@Override
	public int doAfterBody() throws JspException {

		String body = getBodyContent().getString();
		JspWriter out = getBodyContent().getEnclosingWriter();

		if ( cacheBustingEnabled ) {

			output = new String(body);
			
			for( int i=0; i < FILE_REFERENCES.length; i++ ) {
				loopThroughResources(FILE_REFERENCES[i]);
			}
		} 
		
		try {
			if ( output != null ) {
				out.print( output );
			} else {
				out.print( body );
			}
			
		} catch (Exception e ) {
			log.error("Exception : {}", e.getMessage() );
		}
		return SKIP_BODY;
	}
	
	private void loopThroughResources(String fileReferencekey) {
		int beginPos = output.indexOf(fileReferencekey);
		
		
		while ( beginPos != -1 ) {
			
			beginPos += fileReferencekey.length() + 1;
			
			int endPos = output.indexOf("\"", beginPos );
			
			if ( endPos > beginPos ) {
				String filename = output.substring(beginPos,endPos);

				if (filename != null ) {
					getUniqueIdForTheFile(filename);
				}
			}
			beginPos = output.indexOf(fileReferencekey, endPos );
			if ( beginPos != -1 ) {
				beginPos += fileReferencekey.length() + 1;
			}
		}
	}
	
	
	private void getUniqueIdForTheFile( String filename ) {
		
		String newFileName = filename;
		if ( isInlineStyle()) {
			
			StringTokenizer st = new StringTokenizer(newFileName, FILE_SEPARATOR);
			
			if ( st.countTokens() == 2) {
				newFileName = st.nextToken() + "." + uniqueId + "." + st.nextToken();   
			}
		} else if ( isParameterStyle()) {
			newFileName = filename + "?v=" + uniqueId;
		}
		output = StringUtils.replace(output, filename, newFileName );
		
	}


	private boolean isInlineStyle() {
		return STYLE_INLINE.equals(style);
	}
	
	private boolean isParameterStyle() {
		return STYLE_PARAMETER.equals(style);
	}
	
	
	protected void activate(ComponentContext context) throws Exception {
		cacheBustingEnabled = PropertiesUtil.toBoolean(
				context.getProperties().get(PROP_ENABLED), false);
	}
	
	public String getStyle() {
		return style;
	}

	public void setStyle(String style) {
		this.style = style;
	}

	public String getUniqueId() {
		return uniqueId;
	}

	public void setUniqueId(String uniqueId) {
		this.uniqueId = uniqueId;
	}
	
	
}
