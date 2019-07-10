package com.chnoumis.seshat.email.ws.routes.service;

import org.apache.camel.builder.RouteBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import com.chnoumis.seshat.email.constant.Constant;
import com.chnoumis.seshat.email.constant.SEWSReqCode_001;
import com.chnoumis.seshat.email.json.jaxb.MessageJson;
import com.chnoumis.seshat.email.routes.client.HttpClientService;
import com.chnoumis.seshat.email.validation.RequestValidation;
import com.chnoumis.seshat.email.ws.transform.request.TransformRequestSEWS001001;
import com.chnoumis.seshat.email.ws.transform.response.TransformResponseSEWS001001;

@Component(SEWSReqCode_001.SENGRID)
public class ServiceSEWS001001 extends RouteBuilder {

	@Autowired
	private MessageJson osirisMessageJson;
	
	@Value("${sendgrid.url}")
	private String url;

	@Value("${sendgrid.apikey}")
	private String apikey;

	@Autowired
	private TransformRequestSEWS001001 transformRequestSEWS001001;
	
	@Autowired
	private TransformResponseSEWS001001 transformResponseSEWS001001;
	
	@Autowired
	private HttpClientService httpClientService;
	
    @Autowired
    RequestValidation requestValidation;

	@Override
	public void configure() throws Exception {
		from("direct:" + SEWSReqCode_001.SENGRID).routeId(SEWSReqCode_001.SENGRID)
				.bean(requestValidation)
				.setProperty(Constant.API_CONTENT_TYPE, constant("application/json"))
				.setProperty(Constant.API_AUTHORIZATION, constant("Bearer " + this.apikey))
				.setProperty(Constant.API_ENDPOINT, constant(this.url))
				.bean(transformRequestSEWS001001)
				.bean(httpClientService).id(SEWSReqCode_001.SENGRID + "-CALL")
				.bean(transformResponseSEWS001001);
	}

}