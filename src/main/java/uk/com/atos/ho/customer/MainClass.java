package uk.com.atos.ho.customer;

import org.apache.camel.component.language.springboot.LanguageComponentAutoConfiguration;
import org.apache.camel.component.log.springboot.LogComponentAutoConfiguration;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.boot.autoconfigure.jdbc.DataSourceTransactionManagerAutoConfiguration;
import org.springframework.boot.autoconfigure.orm.jpa.HibernateJpaAutoConfiguration;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@EnableAutoConfiguration(exclude = { DataSourceAutoConfiguration.class,
		DataSourceTransactionManagerAutoConfiguration.class, HibernateJpaAutoConfiguration.class,
		LanguageComponentAutoConfiguration.class,LogComponentAutoConfiguration.class })
@ComponentScan(basePackages = { "uk.com.atos.ho.customer.model", "uk.com.atos.ho.customer.service",
		"uk.com.atos.ho.customer.routes" })
public class MainClass {

	public static void main(String args[]) {

		SpringApplication.run(MainClass.class, args);

	}

}
