package com.example.pdfxconversiondemo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


@SpringBootApplication
public class PDFXConversionDemoApplication {

	public static void main(String[] args) {
		Logger logger = LoggerFactory.getLogger(PDFXConversionDemoApplication.class);

		logger.info("Starting PDFX Conversion Application...");
		SpringApplication.run(PDFXConversionDemoApplication.class, args);
	}

}
