package com.chnoumis.seshat.email.ws.transform.response;

import org.apache.camel.Exchange;
import org.apache.camel.Handler;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.chnoumis.seshat.email.constant.Constant;
import com.chnoumis.seshat.email.constant.SEWSRespCode_001;
import com.chnoumis.seshat.email.json.jaxb.MessageJson;
import com.chnoumis.seshat.email.xmlmodel.tws.ResponseComType;

@Service
public class TransformResponseSEWS001001 {

	private static final Logger logger = LogManager.getLogger();

	@Autowired
	private MessageJson messageJson;

	@Handler
	public ResponseComType process(Exchange exchange) throws Exception {

		String payload = exchange.getIn().getBody(String.class);

		ResponseComType response = new ResponseComType();

		Integer statusCode = (Integer) exchange.getProperty(Constant.API_STATUS_CODE);

		switch (statusCode) {
		case 202:
			response.setRespCode(SEWSRespCode_001.SEWS_SUCCESS);
			break;
		default:
			response.setRespCode(SEWSRespCode_001.SEWS_UNHANDLE_ERROR);
		}

		return response;
	}

}