package com.myproject.workflow;

import javax.jcr.Node;
import javax.jcr.Property;
import javax.jcr.PropertyIterator;
import javax.jcr.PropertyType;
import javax.jcr.RepositoryException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.day.cq.workflow.WorkflowSession;
import com.day.cq.workflow.exec.WorkflowData;


public class UploadWorkflowStep {
	
	public static Logger logger = LoggerFactory.getLogger(UploadWorkflowStep.class);
	public static final String tokenName = "__token";
	public static final String adminIndexName = "__adminIndex";
	public static final String userNameProperty = "__userName";
	
	public final String ENCODING = "UTF-8";
	
	public static String getUserName(Node node) {
		String userName = null;
		
		try {
			if ( node != null && node.hasProperty(userNameProperty))
			{
				Property prop= node.getProperty(userNameProperty);
				
				userName =  prop.getString();								
			}
		} catch (RepositoryException e) {
			logger.error("Unable to access repository",e);
		}
		logger.info("Using userName {}",userName);
		
		return userName;
	}
	
	public static int getAdminIndex(Node node) {
		int adminIndex = 1;
		
		try {
			if ( node != null && ! node.hasProperty(adminIndexName) ) {
				System.out.println("Has adminprop : " + node.hasProperty(adminIndexName));
				System.out.println("Pending changes : " + node.getSession().hasPendingChanges());
				System.out.println("Node path : " + node.getPath());
				PropertyIterator it = node.getProperties();
				while (it.hasNext()) {
					System.out.println(it.nextProperty().getName());
				}
			}
			
			if ( node != null && node.hasProperty(adminIndexName))
			{
				Property prop= node.getProperty(adminIndexName);
				
				if ( prop.getType() == PropertyType.LONG)
				{
					adminIndex = (int)prop.getLong();
				}
				else if ( prop.getType() == PropertyType.STRING)
				{
					adminIndex = Integer.parseInt(prop.getString());
				}
				else {
					logger.error("Using default admin index for propertyType {}",prop.getType());
				}				
			}
			else
			{
				logger.error("Unable to get admin index property (name:{})",adminIndexName);
			}
		} catch (RepositoryException e) {
			logger.error("Unable to access repository",e);
		}
		logger.info("Using admin index {}",adminIndex);
		
		return adminIndex;
	}
	
	public static Node getWorkFlowNode(WorkflowSession session,WorkflowData workflowData) {
		
		Node workFlowNode = null;
		String path = null;
		String payloadData =(String)workflowData.getPayload();
		
		try {
			if ( session.getSession().itemExists(payloadData)) {
				path =payloadData;
			}
			if ( path != null )
			{
				workFlowNode = (Node)session.getSession().getItem(path);
			}
		}
		catch (RepositoryException ex)
		{
			logger.error("Unable to retrieve data from path:{}",path);
		}
		
		return workFlowNode;
	}
	
	public static String getToken(WorkflowSession session,WorkflowData workflowData) {
		String token = null;
		try {
			String path = null;
			String payloadData =(String)workflowData.getPayload();
			if ( session.getSession().itemExists(payloadData)) {
				path =payloadData;
			}
			if ( path != null )
			{
				final Node userGeneratedNode = (Node)session.getSession().getItem(path);
				if ( userGeneratedNode.hasProperty(tokenName)) {
					Property tokenProperty = userGeneratedNode.getProperty(tokenName);
					if ( tokenProperty != null )
					{
						return tokenProperty.getString();
					}
				}
			}			
		} catch (Exception ex) {
			logger.info("Unable to get property",ex);
		}
		return token;
	}
	
	public static void setToken(WorkflowSession session,WorkflowData workflowData,String token) {
		try {
			String path = null;
			String payloadData =(String)workflowData.getPayload();
			if ( session.getSession().itemExists(payloadData)) {
				path =payloadData;
			}
			if ( path != null )
			{
				final Node userGeneratedNode = (Node)session.getSession().getItem(path);
				userGeneratedNode.setProperty(tokenName, token);
				session.getSession().save();
			}			
		} catch (Exception ex) {
			logger.info("Unable to store property",ex);
		}
	}
}
