package com.ecomApp.web;

import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.MutationMapping;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.stereotype.Controller;

import com.ecomApp.dto.ProductDto;
import com.ecomApp.entities.Categorie;
import com.ecomApp.entities.Product;
import com.ecomApp.repository.CategorieRepository;
import com.ecomApp.repository.ProductRepository;

@Controller
public class productGraphController {
	@Autowired
	private ProductRepository productRepository;
	@Autowired
	private CategorieRepository categorieRepository;

	@QueryMapping
	public List<Product> productList(){
		return productRepository.findAll();
		}
	
	@QueryMapping
	public List<Categorie> categorieList(){
		return categorieRepository.findAll();
	}
	
	
	@QueryMapping
	public Product productById(@Argument String id){
		return productRepository.findById(id).orElseThrow(()->new RuntimeException(String.format("Product %d not found",id)));
	}
	
	
	@QueryMapping
	public Categorie categorieById(@Argument Long id) {
		return categorieRepository.findById(id).orElseThrow(()-> new RuntimeException(String.format("Categorie %d not found",id)));
	}
	
	@MutationMapping
	public Product saveProduct(@Argument ProductDto product) {
		Categorie categorie = categorieRepository.findById(product.getCategorieId()).orElse(null);
		Product productSave = new Product();
		productSave.setId(UUID.randomUUID().toString());
		productSave.setName(product.getName());
		productSave.setPrice(product.getPrice());
		productSave.setQuantity(product.getQuantity());
		productSave.setCategorie(categorie);
		
		return productRepository.save(productSave);
	}
	
	@MutationMapping
	public Product updateProduct(@Argument String id, @Argument ProductDto product) {
		Categorie categorie = categorieRepository.findById(product.getCategorieId()).orElse(null);
		Product productSave = new Product();
		productSave.setId(id);
		productSave.setName(product.getName());
		productSave.setPrice(product.getPrice());
		productSave.setQuantity(product.getQuantity());
		productSave.setCategorie(categorie);
		
		return productRepository.save(productSave);
	}
	
	
	@MutationMapping
	public void deleteProduct(@Argument String id) {
		productRepository.deleteById(id);
	}
	
	
}
