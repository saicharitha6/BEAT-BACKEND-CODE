package org.accolite;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@Slf4j
@SpringBootApplication(scanBasePackages = {"org.accolite"})
public class BeatApplication {
	public static void main(String[] args) {
		SpringApplication.run(BeatApplication.class, args);
		log.debug("hello world printed");
		System.out.print("hello world");
	}
}
