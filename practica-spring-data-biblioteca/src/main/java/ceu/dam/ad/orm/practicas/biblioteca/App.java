package ceu.dam.ad.orm.practicas.biblioteca;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

import ceu.dam.ad.orm.practicas.biblioteca.test.TestBiblioteca;

@SpringBootApplication
public class App {

	public static void main(String[] args) {
		ConfigurableApplicationContext context = SpringApplication.run(App.class, args);
		// context.getBean(TestBiblioteca.class).test();
	}

}
