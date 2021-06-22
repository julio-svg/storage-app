package com.project.storage.storagems;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;


@SpringBootApplication(scanBasePackages = {"com.project.storage.commons" , "com.project.storage.storagems"})
public class StorageMsApplication {

	public static void main(String[] args) {
		SpringApplication.run(StorageMsApplication.class, args);
	}

}
