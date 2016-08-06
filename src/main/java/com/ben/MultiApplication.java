package com.ben;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.ben.domain.mysql.ProductDao;

@SpringBootApplication
public class MultiApplication implements CommandLineRunner {

	public static void main(String[] args) {
		SpringApplication.run(MultiApplication.class, args);
	}
	@Override
	public void run(String... args) throws Exception {
		
	}
	

}
