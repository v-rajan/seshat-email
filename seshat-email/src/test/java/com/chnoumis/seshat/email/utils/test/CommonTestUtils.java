package com.chnoumis.seshat.email.utils.test;

import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Paths;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.chnoumis.seshat.email.json.jaxb.MessageJson;
import com.chnoumis.seshat.email.xmlmodel.tws.RequestComType;
import com.chnoumis.seshat.email.xmlmodel.tws.ResponseComType;

@Service
public class CommonTestUtils {

	private static Logger logger = LogManager.getLogger();

	@Autowired
	MessageJson messageJson;

	public static String readFile(String path, Charset encoding) throws IOException {
		byte[] encoded = Files.readAllBytes(Paths.get(path));
		return new String(encoded, encoding);
	}

	public RequestComType createRequestFromFile(String fileName) throws Exception {
		String payload = new String(Files.readAllBytes(Paths.get(fileName)));
		RequestComType request = (RequestComType) this.messageJson.unmarshallerJsonRequest(payload);
		return request;
	}

	public void logRequest(RequestComType request) throws Exception {
		logger.info(messageJson.marshallerJsonRequest(request));
	}

	public void logResponseComType(ResponseComType response) throws Exception {
		logger.info(messageJson.marshallerJsonResponse(response));
	}

}