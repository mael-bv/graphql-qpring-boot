package com.ecomApp.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ecomApp.entities.Categorie;

public interface CategorieRepository extends JpaRepository<Categorie, Long> {

}
