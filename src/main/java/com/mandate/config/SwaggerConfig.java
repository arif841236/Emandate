package com.mandate.config;

import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.google.common.base.Predicates;

import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;

@Configuration
public class SwaggerConfig {
	private static final Set<String> producerAndConsumer = new HashSet<>(Arrays.asList("application/json"));
	
	@Bean
	public Docket api() {

		return new Docket(DocumentationType.SWAGGER_2)
				.apiInfo(getInfo())
				.select()
				.apis(RequestHandlerSelectors.any())
				.paths(PathSelectors.ant("/manage.*"))
				.build().produces(producerAndConsumer).consumes(producerAndConsumer);
	}

	public ApiInfo getInfo() {

		return new ApiInfo("Emandate Api Documentation", "Api Documentation", "1.0"
				, "Terms of Service"
				, null
				, "Apache 2.0", "https://www.apache.org/licenses/LICENSE-2.0", Collections.emptyList());
	}
}
