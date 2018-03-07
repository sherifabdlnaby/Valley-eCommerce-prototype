package com.piper.valley;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;


@SpringBootApplication
@EnableJpaRepositories(basePackages="com.piper.valley.models.repository")
@EnableJpaAuditing
public class ValleyApplication {

	public static void main(String[] args) {
		SpringApplication.run(ValleyApplication.class, args);
	}
}
