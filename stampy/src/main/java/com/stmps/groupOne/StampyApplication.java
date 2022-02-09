package com.stmps.groupOne;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;

import com.stmps.groupOne.models.User;
import com.stmps.groupOne.services.RoleService;
import com.stmps.groupOne.services.UserService;
import com.stmps.groupOne.storage.StorageProperties;
import com.stmps.groupOne.storage.StorageService;

@SpringBootApplication
@EnableConfigurationProperties(StorageProperties.class)
public class StampyApplication {

	public static void main(String[] args) {
		SpringApplication.run(StampyApplication.class, args);
	}
	
	@Bean
	CommandLineRunner init(StorageService storageService, StampyConfiguration stmpdConf) {
		return (args) -> {
//			storageService.deleteAll();
			storageService.init();
			stmpdConf.init();
		};
	}
}
