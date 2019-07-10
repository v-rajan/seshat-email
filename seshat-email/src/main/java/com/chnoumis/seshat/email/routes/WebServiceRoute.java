package com.chnoumis.seshat.email.routes;

import org.apache.camel.LoggingLevel;
import org.apache.camel.builder.RouteBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.chnoumis.seshat.email.constant.Constant;
import com.chnoumis.seshat.email.exception.ExceptionController;
import com.chnoumis.seshat.email.log.Logging;
import com.chnoumis.seshat.email.ws.transform.TransformResponse;

@Component
public class WebServiceRoute extends RouteBuilder {

    @Autowired
    Logging logging;

    @Autowired
    TransformResponse transformResponse;

    @Autowired
	ExceptionController exceptionController;

    @Override
    public void configure() throws Exception {
        from("jetty:{{service.url}}?matchOnUriPrefix=true&amp;enableCORS=true").routeId(Constant.WEB_SERVICE_ROUTE)
                .doTry()
                .bean(logging)
                .to("direct:controller").id(Constant.WEB_SERVICE_ROUTE + "-controller")
                .bean(transformResponse).id(Constant.WEB_SERVICE_ROUTE + "-transform-response")
                .log("Outgoing payload : ${body}")
                .doCatch(Exception.class)
                .log(LoggingLevel.ERROR, "${exception.stacktrace}")
                .bean(exceptionController)
                .log("${body}");
    }
}
