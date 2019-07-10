package com.chnoumis.seshat.email.log;

import java.util.UUID;

import org.apache.camel.Exchange;
import org.apache.camel.Handler;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.ThreadContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.chnoumis.seshat.email.constant.Constant;
import com.chnoumis.seshat.email.json.jaxb.MessageJson;
import com.chnoumis.seshat.email.xmlmodel.tws.RequestComType;

@Service
public class Logging {

    private static final Logger logger = LogManager.getLogger();

    @Autowired
    private MessageJson osirisMessageJson;

    @Handler
    public void process(Exchange exchange) throws Exception {
        String body = exchange.getIn().getBody(String.class);

        RequestComType tempRequest = (RequestComType) osirisMessageJson.unmarshallerJsonRequest(body);

        if (tempRequest.getMessageId() == null) {
            tempRequest.setMessageId(UUID.randomUUID().toString());
        }
        ThreadContext.put("message.id", tempRequest.getMessageId());

        logger.info("Incoming payload : " + body);

        exchange.getOut().setHeaders(exchange.getIn().getHeaders());
        exchange.getOut().setHeader(Constant.MESSAGE_ID, tempRequest.getMessageId());
        exchange.getOut().setBody(tempRequest);
    }

}