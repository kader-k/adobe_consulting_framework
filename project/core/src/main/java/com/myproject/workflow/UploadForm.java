package com.myproject.workflow;

import javax.jcr.Node;

import org.apache.felix.scr.annotations.Component;
import org.apache.felix.scr.annotations.Properties;
import org.apache.felix.scr.annotations.Property;
import org.apache.felix.scr.annotations.Service;
import org.osgi.framework.Constants;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.day.cq.workflow.WorkflowException;
import com.day.cq.workflow.WorkflowSession;
import com.day.cq.workflow.exec.WorkItem;
import com.day.cq.workflow.exec.WorkflowData;
import com.day.cq.workflow.exec.WorkflowProcess;
import com.day.cq.workflow.metadata.MetaDataMap;

@Component
@Service
@Properties({
	@Property(name=Constants.SERVICE_DESCRIPTION, value="Upload form to Upload Service"),
	@Property(name=Constants.SERVICE_VENDOR, value="Vixion B.V."),
	@Property(name = "process.label", value="UploadForm workflow process")}
		)
public class UploadForm extends UploadWorkflowStep implements  WorkflowProcess{

	private static Logger logger =LoggerFactory.getLogger(UploadForm.class);
	
	@Override
	public void execute(WorkItem item, WorkflowSession session, MetaDataMap args)
			throws WorkflowException {

		WorkflowData workflowData = item.getWorkflowData();
		
		logger.info(workflowData.getPayload().toString());
		
		Node workFlowNode = getWorkFlowNode(session, workflowData);
		int adminIndex = getAdminIndex(workFlowNode);
		
		logger.info("adminIndex : " + adminIndex);
		//System.out.println("adminIndex: " + adminIndex);
		
	}

}
