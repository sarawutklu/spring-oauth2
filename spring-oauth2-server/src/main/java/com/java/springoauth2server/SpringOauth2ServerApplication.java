package com.java.springoauth2server;

import com.java.springoauth2server.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
@SpringBootApplication
public class SpringOauth2ServerApplication {
	public static void main(String[] args) {
		SpringApplication.run(SpringOauth2ServerApplication.class, args);
	}

}
