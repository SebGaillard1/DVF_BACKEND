package com.example.DVFPROJECT;

import com.example.DVFPROJECT.service.impl.CsvDataServiceImpl;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class DvfProjectApplication {

	public static void main(String[] args) {
		SpringApplication.run(DvfProjectApplication.class, args);
		CsvDataServiceImpl reader = new CsvDataServiceImpl();
		String filePath = "full.csv";
		reader.readCsvData(filePath);
	}

}
