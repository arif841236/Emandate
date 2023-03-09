package com.mandate;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@SpringBootApplication
@EnableSwagger2
public class MandateApplication {

	public static void main(String[] args) {
		SpringApplication.run(MandateApplication.class, args);
	}

}
