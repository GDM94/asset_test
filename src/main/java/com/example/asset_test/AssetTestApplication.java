package com.example.asset_test;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.security.crypto.password.PasswordEncoder;



@SpringBootApplication
public class AssetTestApplication {

	private static final Logger logger = LoggerFactory.getLogger(AssetTestApplication.class);


	public static void main(String[] args) {
		logger.info("Hello from Logback");

		SpringApplication.run(AssetTestApplication.class, args);
	}

}
