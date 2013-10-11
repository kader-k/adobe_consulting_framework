package com.myproject.statistics;

import javax.jcr.Node;
import javax.jcr.RepositoryException;

import com.day.crx.statistics.Entry;
import com.day.crx.statistics.PathBuilder;

public class CustomEntry extends Entry {

	public CustomEntry(String pathPrefix) {
		super(pathPrefix);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void write(Node node) throws RepositoryException {
		
		// TODO Auto-generated method stub
		super.write(node);
	}

	@Override
	protected PathBuilder getPathBuilder() {
		// TODO Auto-generated method stub
		return null;
	}

}
