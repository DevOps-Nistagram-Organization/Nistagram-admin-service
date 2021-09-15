package com.nistagram.administrator;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients
public class AdministratorApplication {

	public static void main(String[] args) {
		SpringApplication.run(AdministratorApplication.class, args);
	}

}
