package net.pronto.librarymanagementservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;

@SpringBootApplication
@EnableWebSecurity
public class LibraryManagementServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(LibraryManagementServiceApplication.class, args);
	}

}