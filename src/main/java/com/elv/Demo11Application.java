package com.elv;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;

import com.elv.config.TokenAuthenticationService;

@SpringBootApplication
@EnableWebSecurity
public class Demo11Application {

	public static void main(String[] args) {
		SpringApplication.run(Demo11Application.class, args);
	}
	
	@Bean
	public TokenAuthenticationService getTokenAuthenticationService() {
		return new TokenAuthenticationService();
	}
}
