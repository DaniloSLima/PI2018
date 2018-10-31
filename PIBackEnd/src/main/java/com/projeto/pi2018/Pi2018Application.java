package com.projeto.pi2018;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
@ComponentScan(basePackages="com.projeto.pi2018")
public class Pi2018Application {

	public static void main(String[] args) {
		SpringApplication.run(Pi2018Application.class, args);
	}
}
