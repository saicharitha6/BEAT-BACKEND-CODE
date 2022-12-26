package org.accolite;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;

@SpringBootApplication(exclude = {DataSourceAutoConfiguration.class })
public class BeatApplication {
	public static void main(String[] args) {
		Logger log = LoggerFactory.getLogger(BeatApplication.class);
		SpringApplication.run(BeatApplication.class, args);
		log.debug("hello printed");
		System.out.println("hello world");
	}
}
