package com.gt.cds;

import com.gt.cds.service.UserSvc;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class CdsApplication {

	@Autowired
	private UserSvc userSvc;

	public static void main(String[] args) {
		SpringApplication.run(CdsApplication.class, args);
	}


}
