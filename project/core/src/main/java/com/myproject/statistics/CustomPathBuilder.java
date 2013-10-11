package com.myproject.statistics;

import com.day.crx.statistics.Entry;
import com.day.crx.statistics.PathBuilder;

public class CustomPathBuilder extends PathBuilder {

    public CustomPathBuilder(String timestampPattern) {
		super(timestampPattern);
	}

	/** The name of the node that contains the statistical data about a page */
    public static final String STATS_NAME = ".stats";

	@Override
	public void formatPath(Entry entry, StringBuffer buffer) {
		
        buffer.append(entry.getPathPrefix());
        buffer.append("/").append(STATS_NAME).append("/");
		
		super.formatPath(entry, buffer);
	}
}
