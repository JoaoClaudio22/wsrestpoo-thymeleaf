package com.example.demo.webservice.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.webservice.model.Curso;


/*
 * A interface ICursosRepository vai herdar os métodos da classe JpaRepository, contendo tudo que precisa para as operações do CRUD 
 */

@Repository // Classe de persistencia que será gerenciada pelo spring
public interface ICursosRepository extends JpaRepository<Curso, Long> {

}
