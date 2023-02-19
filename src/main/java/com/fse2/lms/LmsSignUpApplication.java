package com.fse2.lms;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;

@SpringBootApplication(exclude = {SecurityAutoConfiguration.class })
public class LmsSignUpApplication {

	public static void main(String[] args) {
		SpringApplication.run(LmsSignUpApplication.class, args);
	}

}
