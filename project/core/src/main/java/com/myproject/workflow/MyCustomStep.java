package com.myproject.workflow;

import java.util.List;

import javax.jcr.Node;
import javax.jcr.RepositoryException;
import javax.jcr.Session;

import org.apache.felix.scr.annotations.Component;
import org.apache.felix.scr.annotations.Properties;
import org.apache.felix.scr.annotations.Property;
import org.apache.felix.scr.annotations.Service;
import org.osgi.framework.Constants;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.day.cq.workflow.WorkflowException;
import com.day.cq.workflow.WorkflowSession;
import com.day.cq.workflow.exec.Route;
import com.day.cq.workflow.exec.WorkItem;
import com.day.cq.workflow.exec.WorkflowData;
import com.day.cq.workflow.exec.WorkflowProcess;
import com.day.cq.workflow.metadata.MetaDataMap;

@Component(immediate = true, metatype = true)
@Service
@Properties({
		@Property(name = Constants.SERVICE_DESCRIPTION, value = "Sample custom workflow step"),
		@Property(name = Constants.SERVICE_VENDOR, value = "Adobe"),
		@Property(name = "process.label", value = "Feike Test") })
public class MyCustomStep implements WorkflowProcess {

	private static final Logger log = LoggerFactory.getLogger(MyCustomStep.class);
	private static final String TYPE_JCR_PATH = "JCR_PATH";

	@Override
	public void execute(final WorkItem item,
			final WorkflowSession workflowSession, final MetaDataMap args)
			throws WorkflowException {

		final WorkflowData data = item.getWorkflowData();
		final Session session = workflowSession.getSession();
		String path = null;

		String type = data.getPayloadType();
		System.out.println("items: " +  workflowSession.getActiveWorkItems().length);

		if (type.equals(TYPE_JCR_PATH) && data.getPayload() != null) {
        	String payloadData = (String) data.getPayload();
        	
			try {
				if (session.itemExists(payloadData)) {
				    path = payloadData;
				}
				Node n = session.getNode(path + "/jcr:content/vlt:definition/filter");
				
				System.out.println( "Node path : " + n.getPath() );
				System.out.println( "Subnodes  : " + n.hasNodes() );
				
			} catch (RepositoryException e) {
				log.error( "Error in MyCustomStep", e );
			}
		}
		List<Route> routes = workflowSession.getRoutes(item);
		if ( ! routes.isEmpty()) {
			workflowSession.complete(item, routes.get(0));
		}
	}
	
}
