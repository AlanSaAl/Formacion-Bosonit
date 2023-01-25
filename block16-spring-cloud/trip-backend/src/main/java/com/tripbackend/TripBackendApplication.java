package com.tripbackend;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;


@EnableEurekaServer
@SpringBootApplication
public class TripBackendApplication {

	public static void main(String[] args) {
		SpringApplication.run(TripBackendApplication.class, args);
	}

}
