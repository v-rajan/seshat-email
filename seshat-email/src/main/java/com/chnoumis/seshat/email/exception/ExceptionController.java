package com.chnoumis.seshat.email.exception;

import org.apache.camel.Exchange;
import org.apache.camel.Handler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.chnoumis.seshat.email.constant.SEWSRespCode_001;
import com.chnoumis.seshat.email.json.jaxb.MessageJson;
import com.chnoumis.seshat.email.xmlmodel.tws.ResponseComType;

@Service
public class ExceptionController {

	@Autowired
	private MessageJson osirisMessageJson;
	
    @Handler
    public String process(Exchange exchange) throws Exception {
        
    	Exception cause = exchange.getProperty(Exchange.EXCEPTION_CAUGHT, Exception.class);
		
    	ResponseComType response = new ResponseComType();
		response.setRespCode(SEWSRespCode_001.SEWS_UNHANDLE_ERROR);
		
		if (cause instanceof RequestElementException) {
			response.setRespCode(SEWSRespCode_001.SEWS_EXCEPTION);
		} else {
			// TODO : Map Error response
		}

		return this.osirisMessageJson.marshallerJsonResponse(response);
	}
}
