package com.chnoumis.seshat.email.ws.transform;

import org.apache.camel.Exchange;
import org.apache.camel.Handler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.chnoumis.seshat.email.constant.Constant;
import com.chnoumis.seshat.email.json.jaxb.MessageJson;
import com.chnoumis.seshat.email.xmlmodel.tws.ResponseComType;

@Service
public class TransformResponse {

	@Autowired
	private MessageJson osirisMessageJson;

	@Handler
	public String process(Exchange exchange) throws Exception {
		ResponseComType response = exchange.getIn().getBody(ResponseComType.class);
		String messageId = (String) exchange.getIn().getHeader(Constant.MESSAGE_ID);
		response.setMessageId(messageId);
		return osirisMessageJson.marshallerJsonResponse(response);
	}
}