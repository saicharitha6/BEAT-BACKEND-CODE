package com.accolite.beat;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;

@SpringBootApplication(exclude = {DataSourceAutoConfiguration.class })
public class BeatApplication {

	public static void main(String[] args) {
		SpringApplication.run(BeatApplication.class, args);
		System.out.println("hello world");
	}

}
