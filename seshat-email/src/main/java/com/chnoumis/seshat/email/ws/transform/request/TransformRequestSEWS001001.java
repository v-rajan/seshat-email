package com.chnoumis.seshat.email.ws.transform.request;

import java.net.http.HttpRequest.BodyPublisher;
import java.net.http.HttpRequest.BodyPublishers;
import java.util.List;
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
public class TransformRequestSEWS001001 {

	/**
	 * Build the BodyPublisher for SendGrid to be used by the HTTP client
	 *
	 */
	@Handler
	public BodyPublisher process(Exchange exchange) throws Exception {
		RequestComType request = exchange.getIn().getBody(RequestComType.class);

		Mail mail = new Mail();

		Email fromEmail = new Email();

		// Add from
		fromEmail.setEmail(request.getEmail().getFrom().getEmail());

		// Add name if present
		Optional.ofNullable(request.getEmail().getFrom().getName()).ifPresent(e -> {
			fromEmail.setName(request.getEmail().getFrom().getName());
		});

		mail.setFrom(fromEmail);

		// Add subject
		// TODO : Use external template to the following with msgId and Fields from
		// request.
		mail.setSubject("SendGrid : " + UUID.randomUUID().toString());

		Personalization personalization = new Personalization();

		// Add to
		request.getEmail().getTo().forEach(t -> {
			personalization.addTo(this.createEmail(t.getEmail(), t.getName()));
		});

		// Add cc
		request.getEmail().getCc().forEach(t -> {
			personalization.addCc(this.createEmail(t.getEmail(), t.getName()));
		});

		// Add bcc
		request.getEmail().getBcc().forEach(t -> {
			personalization.addBcc(this.createEmail(t.getEmail(), t.getName()));
		});

		// Add subject
		// TODO : Use external template to the following with msgId and Fields from
		// request.
		//personalization.setSubject("Personalization : " + UUID.randomUUID().toString());

		// TODO : Use external template to the following with msgId and Fields from
		Content content = new Content();
		content.setType("text/plain");
		content.setValue("some text here");
		mail.addContent(content);
		content.setType("text/html");
		content.setValue("<html><body>some text here</body></html>");
		mail.addContent(content);

		mail.addPersonalization(personalization);

		ObjectMapper objectMapper = new ObjectMapper();

		return BodyPublishers.ofString(objectMapper.writeValueAsString(mail));
	}

	private Email createEmail(String emailAddress, String name) {
		Email email = new Email();
		email.setEmail(emailAddress);
		// Add name if present
		Optional.ofNullable(name).ifPresent(n -> {
			email.setEmail(name);
		});
		return email;
	}

}