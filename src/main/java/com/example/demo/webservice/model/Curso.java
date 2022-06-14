package com.example.demo.webservice.model;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import org.springframework.format.annotation.DateTimeFormat;

import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data //Lombok
@AllArgsConstructor //Lombok
@NoArgsConstructor //Lombok
@Entity //A classe será uma entidade do Banco de dados
public class Curso {
		
		@Id
		@GeneratedValue(strategy = GenerationType.IDENTITY)
		private Long id;
		
		
		private String tituloCurso;
		
		
		@Enumerated(EnumType.STRING) // Salva no BD uma String com o nome da constante;
		private CargaHoraria cargaHoraria;
	
		
		@DateTimeFormat(pattern = "dd/MM/yyyy")
		private Date dataInicio;
}
