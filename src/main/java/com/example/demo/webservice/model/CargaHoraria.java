package com.example.demo.webservice.model;

import com.fasterxml.jackson.annotation.JsonValue;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;


@AllArgsConstructor
@NoArgsConstructor
public enum CargaHoraria {
	
	VINTE_HORAS("20HS"),TRINTA_HORAS("30HS"),QUARENTA_HORAS("40HS"),SESSENTA_HORAS("60HS");
	
	private String horas;
	
	@JsonValue
	public String getHoras() {
		return horas;
	}
	
	
	
	
}
