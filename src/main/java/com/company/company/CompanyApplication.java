package com.company.company;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

import javax.annotation.Nullable;

@ComponentScan("com.company.company")
@SpringBootApplication
public class CompanyApplication {

	public static void main(@Nullable String[] args) {
		SpringApplication.run(CompanyApplication.class, args);
	}
}
