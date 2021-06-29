package com.project.storage.product;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;


@SpringBootApplication(scanBasePackages = {"com.project.storage.commons" , "com.project.storage.product"})
public class StorageMsApplication {

	public static void main(String[] args) {
		SpringApplication.run(StorageMsApplication.class, args);
	}

}
