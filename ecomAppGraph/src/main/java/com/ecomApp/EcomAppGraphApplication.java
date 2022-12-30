package com.ecomApp;

import java.util.List;
import java.util.Random;
import java.util.UUID;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import com.ecomApp.entities.Categorie;
import com.ecomApp.entities.Product;
import com.ecomApp.repository.CategorieRepository;
import com.ecomApp.repository.ProductRepository;

@SpringBootApplication
public class EcomAppGraphApplication {

	public static void main(String[] args) {
		SpringApplication.run(EcomAppGraphApplication.class, args);
	}
	
	@Bean
	CommandLineRunner commandLineRunner(ProductRepository productRepository,CategorieRepository categorieRepository ) {
		return args->{
			Random random = new Random();
			List.of("Computer","Printer","Smartphone").forEach(cat->{
				Categorie categorie = Categorie.builder().name(cat).build();
				categorieRepository.save(categorie);
			});
			
			categorieRepository.findAll().forEach(cat->{
				for(int i = 0; i <10; i++){
					Product product = Product.builder()
							.id(UUID.randomUUID().toString())
							.name("Computer"+i)
							.price(Math.random()*500000)
							.quantity(random.nextInt(100))
							.categorie(cat)
							.build();
					productRepository.save(product);
				}
			});
		};
	}

}
