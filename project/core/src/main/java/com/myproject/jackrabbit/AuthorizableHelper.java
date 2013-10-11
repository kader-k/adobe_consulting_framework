package com.myproject.jackrabbit;

import javax.jcr.RepositoryException;
import javax.jcr.Value;

import org.apache.commons.lang.StringUtils;
import org.apache.jackrabbit.api.security.user.Authorizable;
import org.apache.sling.api.resource.Resource;
import org.apache.sling.api.resource.ResourceResolver;
import org.apache.sling.api.resource.ResourceUtil;
import org.apache.sling.api.resource.ValueMap;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Helper class to convert Value-objects from the {@link Authorizable#getProperty(String)}
 * This method returns by default a Value[], and not just a single Value.
 * 
 */
public class AuthorizableHelper {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(AuthorizableHelper.class);

	private AuthorizableHelper() {
		
	}

	/**
	 * Returns a {@link ValueMap} object based on the {@link Authorizable#getPath()} resource.
	 * 
	 * @param resolver used to get the resource
	 * @param authorizable getPath is used to lookup the resource
	 * @return ValueMap, this is done via {@link ResourceUtil#getValueMap(Resource)}
	 */
	public static ValueMap getValueMap(ResourceResolver resolver, Authorizable authorizable) {
		Resource resource = null;
		try {
			resolver.getResource(authorizable.getPath());
		} catch (Exception e) {
			LOGGER.error("Error in getValueMap", e);
		}
		return ResourceUtil.getValueMap(resource);
		
	}

	/**
	 * Gets the String-value of the given property, if the property can't be found the default is returned
	 * 
	 * @param authorizable
	 * @param property
	 * @param defaultValue
	 * @return
	 */
	public static String getStringProperty(Authorizable authorizable, String property, String defaultValue) {
		Value value = getProperty(authorizable, property);
		if ( value != null) {
			
			try {
				return value.getString();
			} catch (RepositoryException e) {
				LOGGER.error("Error during getStringProperty {}", property, e);
			}
		}
		return defaultValue;
	}
	
	/**
	 * Gets the String-value of the given property, if the property can't be found an empty String is returned
	 * 
	 * @param authorizable
	 * @param property
	 * @param defaultValue
	 * @return
	 */
	public static String getStringProperty(Authorizable authorizable, String property) {
		return getStringProperty(authorizable, property, StringUtils.EMPTY);
	}
	
	/**
	 * Gets the boolean-value of the given property, if the property can't be found the defaultValue is returned
	 * 
	 * @param authorizable
	 * @param property
	 * @param defaultValue
	 * @return
	 */
	public static boolean getBooleanProperty(Authorizable authorizable, String property, boolean defaultValue) {
		Value value = getProperty(authorizable, property);
		if ( value != null) {
			
			try {
				return value.getBoolean();
			} catch (RepositoryException e) {
				LOGGER.error("Error during getBooleanProperty {}", property, e);
			}
		}
		return defaultValue;
	}

	
	/**
	 * Gets the boolean-value of the given property, if the property can't be found false is returned
	 * 
	 * @param authorizable
	 * @param property
	 * @return
	 */
	public static boolean getBooleanProperty(Authorizable authorizable, String property) {
		return getBooleanProperty(authorizable, property, false);
	}
	
	
	/**
	 * Gets the single Value from the {@link Authorizable#getProperty(String)}, the first element ([0]) is returned.
	 * If the property can't be found null is returned.
	 * 
	 * @param authorizable
	 * @param property
	 * @param defaultValue
	 * @return
	 */
	public static Value getProperty(Authorizable authorizable, String property) {
		Value[] values = null;
		try {
			values = authorizable.getProperty(property);
			if ( values != null && values.length > 0) {
				return values[0];
			}
		} catch (RepositoryException e) {
			LOGGER.error("Error in getProperty, property: {}", property, e);
		}
		return null;
	}
	
}
