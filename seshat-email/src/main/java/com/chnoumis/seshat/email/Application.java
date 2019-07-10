package com.chnoumis.seshat.email;

import org.apache.camel.CamelContext;
import org.apache.camel.spring.boot.CamelContextConfiguration;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceTransactionManagerAutoConfiguration;
import org.springframework.boot.autoconfigure.web.servlet.WebMvcAutoConfiguration;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
@EnableAutoConfiguration(exclude = {
		WebMvcAutoConfiguration.class,
		DataSourceTransactionManagerAutoConfiguration.class
})
public class Application {
	
	private static final Logger logger = LogManager.getLogger();


	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	}


	@Bean
	CamelContextConfiguration contextConfiguration() {
		return new CamelContextConfiguration() {
			public void beforeApplicationStart(CamelContext context) {
				context.setUseMDCLogging(true);
				// context.setTracing(true);
			}

			public void afterApplicationStart(CamelContext camelContext) {

			}
		};
	}
}