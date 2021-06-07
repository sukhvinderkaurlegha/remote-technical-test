package com.task.remotetechnicaltest;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@SpringBootApplication
public class RemoteTechnicalTestApplication {

	public static void main(String[] args) {
		SpringApplication.run(RemoteTechnicalTestApplication.class, args);

		log.info("application started");
	}

}
