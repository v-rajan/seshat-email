package com.chnoumis.seshat.email.routes;

import org.apache.camel.builder.RouteBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.chnoumis.seshat.email.json.jaxb.MessageJson;

@Component
public class ControllerRoute extends RouteBuilder {
	
	@Autowired
	private MessageJson osirisMessageJson;

    @Override
    public void configure() throws Exception {

        from("direct:controller").routeId("controller-route")
        
				.setProperty("logRequest", method(osirisMessageJson, "marshallerJsonRequest")).log("${property.logRequest}")
        
        		.choice()

                .when(simple("${in.body.reqCode} contains 'SEWS'"))
                .toD("direct:${in.body.reqCode}");


    }
}
