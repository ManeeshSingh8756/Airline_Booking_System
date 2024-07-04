package com.airline.booking;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.ResourceBundleMessageSource;

@SpringBootApplication
@Configuration
public class Airline_BookigSystemApplication {

	public static void main(String[] args) {
		SpringApplication.run(Airline_BookigSystemApplication.class, args);
		System.out.print("hello coder");
		
	}
	    @Bean
	    public MessageSource messageSource() {
	        ResourceBundleMessageSource messageSource = new ResourceBundleMessageSource();
	        messageSource.setBasename("messages");
	        messageSource.setDefaultEncoding("UTF-8");
	        return messageSource;
	    }

}
