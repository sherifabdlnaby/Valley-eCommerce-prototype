package com.piper.valley;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@EnableAutoConfiguration
@SpringBootApplication
public class ValleyApplication {

	public static void main(String[] args) {
		SpringApplication.run(ValleyApplication.class, args);
	}
}
