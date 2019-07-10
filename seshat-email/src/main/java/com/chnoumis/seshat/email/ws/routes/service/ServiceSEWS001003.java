package com.chnoumis.seshat.email.ws.routes.service;

import org.apache.camel.builder.RouteBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.chnoumis.seshat.email.constant.Constant;
import com.chnoumis.seshat.email.constant.SEWSReqCode_001;
import com.chnoumis.seshat.email.json.jaxb.MessageJson;

@Component(SEWSReqCode_001.SENGRID_MAILGUN)
public class ServiceSEWS001003 extends RouteBuilder {

	
	@Autowired
	MessageJson osirisMessageJson;
	
	@Override
	public void configure() throws Exception {
		from("direct:" + SEWSReqCode_001.SENGRID_MAILGUN).routeId(SEWSReqCode_001.SENGRID_MAILGUN)
		// Copy request for later use if needed
        .setProperty(Constant.REQUEST_COPY, body())

		.setProperty("logRequest", method(osirisMessageJson, "marshallerJsonRequest")).log("${property.logRequest}")
		.doTry()
			.to("direct:" + SEWSReqCode_001.SENGRID)
			.choice()
				.when(simple("${in.body.respCode} != 'SEWS-000-000'"))
					// Reset to original request
					.setBody(exchangeProperty(Constant.REQUEST_COPY))
					.setProperty("logRequest", method(osirisMessageJson, "marshallerJsonRequest")).log("${property.logRequest}")
					.to("direct:" + SEWSReqCode_001.MAILGUN)
			.end()		
        .endDoTry()
		.doCatch(Exception.class)
			// Reset to original request
			.setBody(exchangeProperty(Constant.REQUEST_COPY))
			.to("direct:" + SEWSReqCode_001.MAILGUN);

	}

}