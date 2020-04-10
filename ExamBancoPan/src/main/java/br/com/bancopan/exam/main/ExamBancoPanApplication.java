package br.com.bancopan.exam.main;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan("br.com.bancopan.exam")
public class ExamBancoPanApplication {

	public static void main(String[] args) {
		SpringApplication.run(ExamBancoPanApplication.class, args);
	}

}
