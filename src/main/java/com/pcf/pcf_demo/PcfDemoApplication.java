package com.pcf.pcf_demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

import com.pcf.pcf_demo.controllers.CourseController;
import com.pcf.pcf_demo.controllers.StudentController;

@SpringBootApplication

@EnableJpaRepositories("com.pcf.pcf_demo.repositories")
@ComponentScan("com.pcf.pcf_demo.services")
@ComponentScan(basePackageClasses = CourseController.class)
@ComponentScan(basePackageClasses = StudentController.class)
public class PcfDemoApplication {

	public static void main(String[] args) {
		SpringApplication.run(PcfDemoApplication.class, args);
	}

}
