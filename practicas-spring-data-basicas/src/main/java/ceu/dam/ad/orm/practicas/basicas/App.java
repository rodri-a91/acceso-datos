package ceu.dam.ad.orm.practicas.basicas;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

import ceu.dam.ad.orm.practicas.basicas.ejercicio1.test.Test1;

@SpringBootApplication
public class App {

	public static void main(String[] args) {
		ConfigurableApplicationContext context = SpringApplication.run(App.class, args);
		Test1 test = context.getBean(Test1.class);
		test.test();
	}

}
