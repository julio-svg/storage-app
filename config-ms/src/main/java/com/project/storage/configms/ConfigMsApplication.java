package com.project.storage.configms;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.config.server.EnableConfigServer;

@EnableConfigServer
@SpringBootApplication
public class ConfigMsApplication {

	public static void main(String[] args) {
		SpringApplication.run(ConfigMsApplication.class, args);
	}

}
