package com.chnoumis.seshat.email.ws.routes.service.test;

import org.apache.camel.test.spring.CamelSpringBootRunner;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.annotation.DirtiesContext.ClassMode;

import com.chnoumis.seshat.email.Application;

@RunWith(CamelSpringBootRunner.class)
@SpringBootTest(classes = Application.class)
@DirtiesContext(classMode = ClassMode.AFTER_EACH_TEST_METHOD)
public abstract class ServiceEFWS001Test {

}