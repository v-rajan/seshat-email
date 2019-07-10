package com.chnoumis.seshat.email.ws.routes.service;

import java.util.Base64;

import org.apache.camel.builder.RouteBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import com.chnoumis.seshat.email.constant.Constant;
import com.chnoumis.seshat.email.constant.SEWSReqCode_001;
import com.chnoumis.seshat.email.json.jaxb.MessageJson;
import com.chnoumis.seshat.email.routes.client.HttpClientService;
import com.chnoumis.seshat.email.ws.transform.request.TransformRequestSEWS001002;
import com.chnoumis.seshat.email.ws.transform.response.TransformResponseSEWS001002;

@Component(SEWSReqCode_001.MAILGUN)
public class ServiceSEWS001002 extends RouteBuilder {

	@Autowired
	private MessageJson osirisMessageJson;
	
	@Value("${mailgun.url}")
	private String url;

	@Value("${mailgun.apikey}")
	private String apikey;

	@Autowired
	private TransformRequestSEWS001002 transformRequestSEWS001002;
	
	@Autowired
	private TransformResponseSEWS001002 transformResponseSEWS001002;
	
	@Autowired
	private HttpClientService httpClientService;

	@Override
	public void configure() throws Exception {
		from("direct:" + SEWSReqCode_001.MAILGUN).routeId(SEWSReqCode_001.MAILGUN)
				.setProperty(Constant.API_CONTENT_TYPE, constant("application/x-www-form-urlencoded"))
				.setProperty(Constant.API_AUTHORIZATION, constant("Basic " + Base64.getEncoder().encodeToString(("api:" + this.apikey).getBytes())))
				.setProperty(Constant.API_ENDPOINT, constant(this.url))
				.bean(transformRequestSEWS001002)
				.bean(httpClientService).id(SEWSReqCode_001.MAILGUN + "-CALL")
				.bean(transformResponseSEWS001002);
	}

}