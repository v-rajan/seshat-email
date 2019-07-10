package com.chnoumis.seshat.email.json.jaxb.test;

import static org.junit.jupiter.api.Assertions.assertTrue;

import java.nio.file.Files;
import java.nio.file.Paths;

import org.junit.jupiter.api.Test;

import com.chnoumis.seshat.email.json.jaxb.MessageJson;
import com.chnoumis.seshat.email.xmlmodel.email.FromComType;
import com.chnoumis.seshat.email.xmlmodel.tws.RequestComType;

class MessageJsonImplTest {

	@Test
	public void unmarshallerJsonRequest() throws java.lang.Exception {
		MessageJson messageJson = new MessageJson();
		RequestComType request = (RequestComType) messageJson.unmarshallerJsonRequest(
				new String(Files.readAllBytes(Paths.get("./src/test/resources/payload/SEWS-001-001/request001.json"))));
		assertTrue(request.getReqCode().compareTo("SEWS-001-001") == 0);
		FromComType from = new FromComType();
		from.setEmail("support@chnoumis.com");
		request.getEmail().setFrom(from);
		System.out.println(messageJson.marshallerJsonRequest(request));
	}

}
