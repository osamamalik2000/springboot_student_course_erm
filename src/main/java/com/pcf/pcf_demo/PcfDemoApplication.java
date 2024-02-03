package com.pcf.pcf_demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

import com.pcf.pcf_demo.controllers.CourseController;

@SpringBootApplication

@EnableJpaRepositories("com.pcf.pcf_demo.repositories")
@ComponentScan("com.pcf.pcf_demo.services")
@ComponentScan("com.pcf.pcf_demo.services")
@ComponentScan(basePackageClasses = CourseController.class)
public class PcfDemoApplication {

	public static void main(String[] args) {
		System.out.println("I am running");
		SpringApplication.run(PcfDemoApplication.class, args);
	}

}
