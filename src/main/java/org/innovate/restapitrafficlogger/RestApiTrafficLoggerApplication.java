package org.innovate.restapitrafficlogger;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMethod;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.ResponseMessageBuilder;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;

import java.lang.management.ManagementFactory;
import java.lang.management.RuntimeMXBean;
import java.util.Arrays;
import java.util.Collections;
import java.util.Date;

@SpringBootApplication
public class RestApiTrafficLoggerApplication {

	public static final Logger LOG = LoggerFactory.getLogger(RestApiTrafficLoggerApplication.class);
	public static void main(String[] args) {
		SpringApplication.run(RestApiTrafficLoggerApplication.class, args);
	}


	/**
	 * BeanInfo.
	 */
	public static void beanInfo() {
		RuntimeMXBean runtimeMXBean = ManagementFactory.getRuntimeMXBean();
		LOG.debug("Boot Class Path : {}", runtimeMXBean.getBootClassPath());
		LOG.debug("Library Path : {}", runtimeMXBean.getLibraryPath());
		LOG.debug("Class Path : {}", runtimeMXBean.getClassPath());
	}

	/**
	 * apiInfo.
	 * @return Docket
	 */
	@Bean
	public Docket apiInfo() {
		ApiInfo apiInfo = new ApiInfo("rest-api-traffic-logger",
				"",
				"1.0",
				"",
				new Contact("", "", ""),
				"MIT",
				"",
				Collections.emptyList());
		Docket docket = new Docket(DocumentationType.SWAGGER_2)
				.apiInfo(apiInfo)
				.genericModelSubstitutes(ResponseEntity.class)
				.forCodeGeneration(true)
				.genericModelSubstitutes(ResponseEntity.class)
				.directModelSubstitute(org.joda.time.LocalDate.class, String.class)
				.directModelSubstitute(org.joda.time.LocalDate.class, Date.class)
				.directModelSubstitute(org.joda.time.DateTime.class, Date.class)
				.directModelSubstitute(java.time.LocalDate.class, String.class)
				.directModelSubstitute(java.time.ZonedDateTime.class, Date.class)
				.directModelSubstitute(java.time.LocalDateTime.class, Date.class)
				.select()
				.paths(PathSelectors.any())
				.build();

		docket.useDefaultResponseMessages(false)
				.globalResponseMessage(RequestMethod.GET,
						Arrays.asList(new ResponseMessageBuilder()
										.code(HttpStatus.INTERNAL_SERVER_ERROR
												.value())
										.message(HttpStatus.INTERNAL_SERVER_ERROR
												.getReasonPhrase())
										.build(),
								new ResponseMessageBuilder()
										.code(HttpStatus.FORBIDDEN.value())
										.message(HttpStatus.FORBIDDEN
												.getReasonPhrase())
										.build(),
								new ResponseMessageBuilder()
										.code(HttpStatus.NOT_FOUND
												.value())
										.message(HttpStatus.NOT_FOUND
												.getReasonPhrase())
										.build()));
		return docket;
	}

}
