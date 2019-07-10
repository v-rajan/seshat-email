package com.chnoumis.seshat.email.routes.client;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpRequest.BodyPublisher;
import java.net.http.HttpResponse;
import java.net.http.HttpResponse.BodyHandlers;

import org.apache.camel.Exchange;
import org.apache.camel.Handler;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Service;

import com.chnoumis.seshat.email.constant.Constant;

@Service
public class HttpClientService {

	private static Logger logger = LogManager.getLogger();

	@Handler
	public String send(Exchange exchange) throws Exception {

		String contentType = (String) exchange.getProperty(Constant.API_CONTENT_TYPE);
		String auth = (String) exchange.getProperty(Constant.API_AUTHORIZATION);
		String url = (String) exchange.getProperty(Constant.API_ENDPOINT);
		BodyPublisher bodyPublisher = (BodyPublisher) exchange.getIn().getBody(BodyPublisher.class);

		HttpClient httpClient = HttpClient.newBuilder().build();

		HttpRequest request = HttpRequest.newBuilder().uri(URI.create(url)).header("Content-Type", contentType)
				.header("Authorization", auth).POST(bodyPublisher).build();

		HttpResponse<String> response = httpClient.send(request, BodyHandlers.ofString());

		exchange.setProperty(Constant.API_STATUS_CODE, response.statusCode());

		logger.info("Response status code: " + response.statusCode());
		logger.info("Response headers: " + response.headers());
		logger.info("Response body: " + response.body());

		return response.body();

	}

}
