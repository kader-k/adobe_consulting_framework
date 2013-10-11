package com.adobe.consulting.page;

import java.util.Calendar;
import java.util.Iterator;
import java.util.Locale;

import org.apache.sling.api.resource.Resource;
import org.apache.sling.api.resource.ValueMap;

import com.day.cq.commons.Filter;
import com.day.cq.tagging.Tag;
import com.day.cq.wcm.api.Page;
import com.day.cq.wcm.api.PageManager;
import com.day.cq.wcm.api.Template;
import com.day.cq.wcm.api.WCMException;

/**
 * Wrapper class around the <code>Page</code> interface.
 * This enables you to implement extra functionality on top of the 
 * default Page functionality. 
 *
 */
public abstract class AbstractPage implements Page {
	
	protected Page page;
	
	public void setPage(Page page ) {
		this.page = page;
	}
	
	@Override
	public <AdapterType> AdapterType adaptTo(Class<AdapterType> arg0) {
		return page.adaptTo(arg0);
	}


	@Override
	public String getDescription() {
		return page.getDescription();
	}


	@Override
	public String getNavigationTitle() {
		return page.getNavigationTitle();
	}

	@Override
	public String getPageTitle() {
		return page.getTitle();
	}

	@Override
	public String getPath() {
		return page.getPath();
	}

	@Override
	public PageManager getPageManager() {
		return page.getPageManager();
	}

	@Override
	public Resource getContentResource() {
		return page.getContentResource();
	}

	@Override
	public Resource getContentResource(String s) {
		return page.getContentResource(s);
	}

	@Override
	public Iterator<Page> listChildren() {
		return page.listChildren();
	}

	@Override
	public Iterator<Page> listChildren(Filter<Page> pageFilter) {
		return page.listChildren(pageFilter);
	}

	@Override
	public boolean hasChild(String s) {
		return page.hasChild(s);
	}

	@Override
	public int getDepth() {
		return page.getDepth();
	}

	@Override
	public Page getParent() {
		return page.getParent();
	}

	@Override
	public Page getParent(int i) {
		return page.getParent(i);
	}

	@Override
	public Page getAbsoluteParent(int i) {
		return page.getAbsoluteParent(i);
	}

	@Override
	public ValueMap getProperties() {
		return page.getProperties();
	}

	@Override
	public ValueMap getProperties(String s) {
		return page.getProperties(s);
	}

	@Override
	public String getName() {
		return page.getName();
	}

	@Override
	public String getTitle() {
		return page.getTitle();
	}

	@Override
	public boolean isHideInNav() {
		return page.isHideInNav();
	}

	@Override
	public boolean hasContent() {
		return page.hasContent();
	}

	@Override
	public boolean isValid() {
		return page.isValid();
	}

	@Override
	public long timeUntilValid() {
		return page.timeUntilValid();
	}

	@Override
	public Calendar getOnTime() {
		return page.getOnTime();
	}

	@Override
	public Calendar getOffTime() {
		return page.getOffTime();
	}

	@Override
	public String getLastModifiedBy() {
		return page.getLastModifiedBy();
	}

	@Override
	public Calendar getLastModified() {
		return page.getLastModified();
	}

	@Override
	public String getVanityUrl() {
		return page.getVanityUrl();
	}

	@Override
	public Tag[] getTags() {
		return page.getTags();
	}

	@Override
	public void lock() throws WCMException {
		page.lock();
	}

	@Override
	public boolean isLocked() {
		return page.isLocked();
	}

	@Override
	public String getLockOwner() {
		return page.getLockOwner();
	}

	@Override
	public boolean canUnlock() {
		return page.canUnlock();
	}

	@Override
	public void unlock() throws WCMException {
		page.unlock();
	}

	@Override
	public Template getTemplate() {
		return page.getTemplate();
	}

	@Override
	public Locale getLanguage(boolean b) {
		return page.getLanguage(b);
	}

	@Override
	public Iterator<Page> listChildren(Filter<Page> arg0, boolean arg1) {
		return page.listChildren(arg0, arg1);
	}
	
}
