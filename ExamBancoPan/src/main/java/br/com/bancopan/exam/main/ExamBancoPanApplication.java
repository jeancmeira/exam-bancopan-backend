package br.com.bancopan.exam.main;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@ComponentScan("br.com.bancopan.exam")
@EnableJpaRepositories("br.com.bancopan.exam.persistence.repository")
@EntityScan("br.com.bancopan.exam.persistence.entity")
public class ExamBancoPanApplication {

	public static void main(String[] args) {
		SpringApplication.run(ExamBancoPanApplication.class, args);
	}

}
