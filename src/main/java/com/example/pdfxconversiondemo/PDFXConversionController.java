package com.example.pdfxconversiondemo;

import java.util.Base64;
import java.util.concurrent.atomic.AtomicLong;

import org.springframework.core.io.Resource;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.http.ResponseEntity;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@RestController
public class PDFXConversionController {

	class ConversionResult{
		final String result;
		ConversionResult(String resultParam){
			result=resultParam;
		}

		public String getResult(){
			return result;
		}
	}

	Logger logger = LoggerFactory.getLogger(PDFXConversionController.class);

	private static final String template = "%s";
	private final AtomicLong counter = new AtomicLong();

	@GetMapping("/greeting")
	public Greeting greeting(@RequestParam(value = "name", defaultValue = "World") String name) {
		return new Greeting (counter.incrementAndGet(), String.format(template, name));
	}

	@GetMapping("/convert")
	public ConversionResult convert() {
		
		PDFXConverter converter = new PDFXConverter(counter.incrementAndGet());
		String result = converter.convertLocal();
		return new ConversionResult(String.format(template, result));
	}

	@PostMapping("/convert")
	public ResponseEntity<Resource> convert(@RequestBody String htmlContent) {
		String result;
	 	PDFXConverter converter = new PDFXConverter(counter.incrementAndGet());
		result = converter.convertData(htmlContent);
		byte[] convertedContent = converter.getConvertedData();

    	ByteArrayResource resource = new ByteArrayResource(convertedContent);
    	return ResponseEntity.ok().contentType(MediaType.APPLICATION_OCTET_STREAM).contentLength(resource.contentLength()).body(resource);
	}
}