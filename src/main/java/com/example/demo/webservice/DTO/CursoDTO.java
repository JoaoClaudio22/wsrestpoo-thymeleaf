package com.example.demo.webservice.DTO;

import java.util.Date;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import org.springframework.format.annotation.DateTimeFormat;

import com.example.demo.webservice.model.CargaHoraria;
import com.example.demo.webservice.model.Curso;

import lombok.AllArgsConstructor;
import lombok.Data;



/*/
 * Padrão Data Tranfer Object - DTO -> Classe para transporte de dados (Utilizei no sistema como forma de evitar possíveis Web Parameter Tampering)
 * como questão de segurança
 */
public class CursoDTO {
	@NotBlank
	private String tituloCurso;
	@NotNull
	private CargaHoraria cargaHoraria;
	@NotNull
	@DateTimeFormat(pattern = "dd/MM/yyyy")
	private Date dataInicio;
	
	
	/*
	 * Metodo responsável por receber os valores que sao passados à DTO para a Entity Curso
	 */
	
	public Curso toCurso() {
		Curso curso = new Curso();
		curso.setTituloCurso(this.tituloCurso);
		curso.setCargaHoraria(this.cargaHoraria);
		curso.setDataInicio(this.dataInicio);
		
		return curso;
	}
	
	public Curso toCurso(Curso curso) {
		curso.setTituloCurso(this.tituloCurso);
		curso.setCargaHoraria(this.cargaHoraria);
		curso.setDataInicio(this.dataInicio);
		
		return curso;
	}
	
	/*
	 * Método responsável por passar os dados do objeto curso para a DTO
	 */
	public void fromCurso(Curso curso) {
		this.setTituloCurso(curso.getTituloCurso());
		this.setCargaHoraria(curso.getCargaHoraria());
		this.setDataInicio(curso.getDataInicio());
	}

	public String getTituloCurso() {
		return tituloCurso;
	}

	public void setTituloCurso(String tituloCurso) {
		this.tituloCurso = tituloCurso;
	}

	public CargaHoraria getCargaHoraria() {
		return cargaHoraria;
	}

	public void setCargaHoraria(CargaHoraria cargaHoraria) {
		this.cargaHoraria = cargaHoraria;
	}

	public Date getDataInicio() {
		return dataInicio;
	}

	public void setDataInicio(Date dataInicio) {
		this.dataInicio = dataInicio;
	}
	
	
}
