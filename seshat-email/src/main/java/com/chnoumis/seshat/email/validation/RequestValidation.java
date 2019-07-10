package com.chnoumis.seshat.email.validation;

import org.apache.camel.Exchange;
import org.apache.camel.Handler;
import org.apache.commons.validator.routines.EmailValidator;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.chnoumis.seshat.email.exception.RequestElementException;
import com.chnoumis.seshat.email.json.jaxb.MessageJson;
import com.chnoumis.seshat.email.xmlmodel.tws.RequestComType;
import com.chnoumis.seshat.email.xmlmodel.tws.ResponseComType;

/**
 * TODO : Validate request
 */
@Service
public class RequestValidation {

	private static final Logger logger = LogManager.getLogger();

	@Autowired
	private MessageJson messageJson;

	@Handler
	public RequestComType process(Exchange exchange) throws Exception {

		RequestComType request = exchange.getIn().getBody(RequestComType.class);

		// Validate to email
		request.getEmail().getTo().forEach(t -> {
			this.isValidEmail(t.getEmail());
		});
		
		// TODO : Check duplicate emails and other validation that might break down stream calls.
		
		return request;
	}
	
	private boolean isValidEmail(String email) {

		EmailValidator validator = EmailValidator.getInstance();
		if (validator.isValid(email)) {
		   return true;
		} else {
		   throw new RequestElementException("Invalid email");
		}
	}

}