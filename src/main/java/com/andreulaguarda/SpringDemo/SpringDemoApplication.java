package com.andreulaguarda.SpringDemo;

import com.andreulaguarda.SpringDemo.entity.Book;
import com.andreulaguarda.SpringDemo.repository.BookRepository;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;

import java.time.LocalDate;

@SpringBootApplication
public class SpringDemoApplication {

	public static void main(String[] args) {

		ApplicationContext context = SpringApplication.run(SpringDemoApplication.class, args);
		context.getBean(BookRepository.class);
		BookRepository repository = context.getBean(BookRepository.class);

		// CRUD
		// crear libro
		Book book1 = new Book(null, "¨Homo Deus", "Yuval Noah", 450, 29.99, LocalDate.of(2018, 12, 1), true);
		Book book2 = new Book(null, "¨Homo Sapiens", "Yuval Noah", 450, 29.99, LocalDate.of(2013, 10, 12), true);

		// almacenar libro
		repository.save(book1);
		repository.save(book2);

		// recuperar todos los libros
		System.out.println("Número de libros en la base de datos:" + repository.count());

		// borrar libro
		// repository.deleteById(1L);
		System.out.println("Número de libros en la base de datos:" + repository.count());



	}

	// Configuración Swagger para la generación de documentación Swagger
	@Bean
	public OpenAPI customOpenAPI() {
		return new OpenAPI()
				.info(new Info()
						.title("Spring Boot Book API REST")
						.version("1.0")
						.description("Library API REST documentation.")
						.termsOfService("http://www.spring.io")
						.license(new License().name("Apache 2.0").url("http://www.apache.org/licenses/")));
	}

}
