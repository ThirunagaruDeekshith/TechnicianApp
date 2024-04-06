package com.technicanApp.technicanApp;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
		/*(
		scanBasePackages = {
				"com.spring_course.spring_learning",
				"com.spring_course.spring_learning.*"
		}
)*/
public class TechnicianApplication {


	public static void main(String[] args) {
		SpringApplication.run(TechnicianApplication.class, args);
	}

}
