package com.chnoumis.seshat.email.ws.transform.request;

import java.net.URLEncoder;
import java.net.http.HttpRequest.BodyPublisher;
import java.net.http.HttpRequest.BodyPublishers;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.UUID;

import org.apache.camel.Exchange;
import org.apache.camel.Handler;
import org.springframework.stereotype.Service;

import com.chnoumis.seshat.email.xmlmodel.tws.RequestComType;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.sendgrid.Content;
import com.sendgrid.Email;
import com.sendgrid.Mail;
import com.sendgrid.Personalization;

@Service
public class TransformRequestSEWS001002 {

	/**
	 * Build the BodyPublisher for Mailguru to be used by the HTTP client
	 *
	 */
	@Handler
	public BodyPublisher process(Exchange exchange) throws Exception {
		RequestComType request = exchange.getIn().getBody(RequestComType.class);

		Map<Object, Object> data = new HashMap<>();

		// Add from
		data.put("from", request.getEmail().getFrom().getEmail());
		
		// Add to
		request.getEmail().getTo().forEach(t -> {
			data.put("to", t.getEmail());
		});

		// Add cc
		request.getEmail().getCc().forEach(t -> {
			data.put("cc", t.getEmail());
		});

		// Add bcc
		request.getEmail().getBcc().forEach(t -> {
			data.put("bcc", t.getEmail());
		});

		data.put("subject", "Mailgun : " + UUID.randomUUID().toString());
		data.put("text", "Testing some Mailgun awesomeness!");

		return this.ofFormData(data);
	}

	public BodyPublisher ofFormData(Map<Object, Object> data) {
		var builder = new StringBuilder();
		for (Map.Entry<Object, Object> entry : data.entrySet()) {
			if (builder.length() > 0) {
				builder.append("&");
			}
			builder.append(URLEncoder.encode(entry.getKey().toString(), StandardCharsets.UTF_8));
			builder.append("=");
			builder.append(URLEncoder.encode(entry.getValue().toString(), StandardCharsets.UTF_8));
		}
		return BodyPublishers.ofString(builder.toString());
	}

}