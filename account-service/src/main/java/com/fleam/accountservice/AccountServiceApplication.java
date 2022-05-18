package com.fleam.accountservice;

import com.fleam.accountservice.repository.UserRepository;
import com.fleam.accountservice.repository.WatchingRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@SpringBootApplication
@EnableEurekaClient
public class AccountServiceApplication {

	@Autowired
	UserRepository account_repo;

	@Autowired
	WatchingRepository watching_repo;


	public static void main(String[] args) {
		SpringApplication.run(AccountServiceApplication.class, args);
	}




}
