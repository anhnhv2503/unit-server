package com.anhnhv.unit.server;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(scanBasePackages = "com.anhnhv.unit.server")
public class UnitServerApplication {

	public static void main(String[] args) {
		SpringApplication.run(UnitServerApplication.class, args);
	}

}
