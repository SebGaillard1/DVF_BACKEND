package com.example.DVFPROJECT;

import com.example.DVFPROJECT.service.TransactionService;
import com.example.DVFPROJECT.service.impl.CsvDataServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class DvfProjectApplication implements CommandLineRunner {
	@Autowired
	private TransactionService transactionService;

	public static void main(String[] args) {
		SpringApplication.run(DvfProjectApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
	}

}
