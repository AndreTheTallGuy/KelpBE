package com.Kelp2.kelp;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;

@SpringBootApplication
@ServletComponentScan
public class KelpApplication {


	public static void main(String[] args) {
		SpringApplication.run(KelpApplication.class, args);
	}

}
