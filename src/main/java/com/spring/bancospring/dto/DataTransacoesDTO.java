package com.spring.bancospring.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class DataTransacoesDTO {

	private String dataInicial="";

	private String dataFinal="";

	public DataTransacoesDTO(String dataInicial, String dataFinal) {
		this.dataInicial = dataInicial;
		this.dataFinal = dataFinal;
	}
}
