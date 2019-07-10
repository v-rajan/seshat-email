package com.chnoumis.seshat.email.ws.routes.service.test;

import static org.junit.jupiter.api.Assertions.assertTrue;

import java.nio.file.Files;
import java.nio.file.Paths;

import org.apache.camel.EndpointInject;
import org.apache.camel.Produce;
import org.apache.camel.ProducerTemplate;
import org.apache.camel.builder.AdviceWithRouteBuilder;
import org.apache.camel.component.mock.MockEndpoint;
import org.apache.camel.model.ModelCamelContext;
import org.apache.camel.model.RouteDefinition;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import com.chnoumis.seshat.email.constant.Constant;
import com.chnoumis.seshat.email.constant.SEWSReqCode_001;
import com.chnoumis.seshat.email.json.jaxb.MessageJson;
import com.chnoumis.seshat.email.xmlmodel.tws.RequestComType;
import com.chnoumis.seshat.email.xmlmodel.tws.ResponseComType;

public class ServiceSEWS001001Test extends ServiceEFWS001Test {

	private static final Logger logger = LoggerFactory.getLogger(ServiceSEWS001001Test.class);
	@Autowired
	protected ModelCamelContext camelContext;

	@Produce(uri = "direct:controller")
	protected ProducerTemplate producerTemplate;

	@Autowired
	MessageJson messageJson;

	@EndpointInject(uri = "mock:result01")
	private MockEndpoint mockEndpoint01;

	public Object handleRequestTest(RequestComType request, Integer statusCode) throws Exception {
		camelContext.setTracing(true);

		RouteDefinition routeA = camelContext.getRouteDefinition(SEWSReqCode_001.SENGRID);

		routeA.adviceWith(camelContext, new AdviceWithRouteBuilder() {
			@Override
			public void configure() throws Exception {
				weaveById(SEWSReqCode_001.SENGRID + "-CALL").replace().to(mockEndpoint01)
						.setProperty(Constant.API_STATUS_CODE, constant(statusCode));
			}
		});

		return producerTemplate.requestBody(request);

	}

	@Test
	public void sucessTest01() throws Exception {

		String payload = new String(
				Files.readAllBytes(Paths.get("./src/test/resources/payload/SEWS-001-001/request001.json")));

		RequestComType request = (RequestComType) this.messageJson.unmarshallerJsonRequest(payload);

		ResponseComType response = (ResponseComType) this.handleRequestTest(request, 202);

		logger.info("response : " + this.messageJson.marshallerJsonResponse(response));
		
		assertTrue(response.getRespCode().compareTo("SEWS-000-000") == 0);
	}
	
	@Test
	public void faliureTest01() throws Exception {

		String payload = new String(
				Files.readAllBytes(Paths.get("./src/test/resources/payload/SEWS-001-001/request001.json")));

		RequestComType request = (RequestComType) this.messageJson.unmarshallerJsonRequest(payload);

		ResponseComType response = (ResponseComType) this.handleRequestTest(request, 400);

		logger.info("response : " + this.messageJson.marshallerJsonResponse(response));
		
		assertTrue(response.getRespCode().compareTo("SEWS-000-002") == 0);

	}
}