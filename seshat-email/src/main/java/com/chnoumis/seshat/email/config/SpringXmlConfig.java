package com.chnoumis.seshat.email.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.ImportResource;

@Configuration
@ComponentScan({ "com.chnoumis.seshat.sendgrid" })
@ImportResource({ "classpath:seshat-configuration.xml" })
public class SpringXmlConfig {

}
