package eu.openminted.registry.messaging;

import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.TextMessage;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import eu.openminted.messageservice.connector.MessagesHandler;
//import eu.openminted.messageservice.messages.GSON;
import com.google.gson.Gson;
import eu.openminted.messageservice.messages.WorkflowExecutionStatusMessage;
import eu.openminted.registry.domain.operation.Corpus;
import eu.openminted.registry.domain.operation.Date;
import eu.openminted.registry.domain.operation.Operation;
//import eu.openminted.registry.service.OperationServiceImpl;

@Component
public class OperationHandler implements MessagesHandler {

	static final Logger logger = Logger.getLogger(OperationHandler.class);

	//@Autowired
	//OperationServiceImpl operationService;
	
		
	@Override
	public void handleMessage(Message msg) {
		
		try{
			
			if (msg instanceof TextMessage) {
				// Extract message as text 
				TextMessage textMessage = (TextMessage) msg;
				logger.info("text message:" + textMessage.getText());
				
				// Transform text message to message object (aka WorkflowExecutionStatusMessage)
				Gson gson = new Gson();
				WorkflowExecutionStatusMessage workflowExecutionMsg = gson.fromJson(textMessage.getText(), WorkflowExecutionStatusMessage.class);
				logger.info("Received message :: " + workflowExecutionMsg.toString() );
			
				// Generate appropriate Operation object
				Operation operation = new Operation();
				if (workflowExecutionMsg.getWorkflowStatus().equalsIgnoreCase("PENDING")) {
					operation.setId(workflowExecutionMsg.getWorkflowExecutionID());					
					operation.setJob(workflowExecutionMsg.getWorkflowExecutionID());
					operation.setPerson(workflowExecutionMsg.getUserID());
					Corpus corpus = new Corpus();
					corpus.setInput(workflowExecutionMsg.getCorpusID());
					operation.setCorpus(corpus);
					operation.setComponent(workflowExecutionMsg.getWorkflowID());
					Date date = new Date();
					date.setSubmitted(new Integer((int)System.currentTimeMillis()));
					operation.setDate(date);
					logger.info("Operation to insert ::" + operation.toString());
				}
						      
				
			}
			else {
				logger.info("Handling a non text message :: " + msg.toString());;
			
			}
		}catch(JMSException e){
	    	logger.info(e.getMessage());	    	
	    }
	}

}
