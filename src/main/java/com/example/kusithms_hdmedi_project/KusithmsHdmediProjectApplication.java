package com.example.kusithms_hdmedi_project;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@EnableJpaAuditing
@SpringBootApplication
public class KusithmsHdmediProjectApplication {

	public static void main(String[] args) {
		SpringApplication.run(KusithmsHdmediProjectApplication.class, args);
	}

}
