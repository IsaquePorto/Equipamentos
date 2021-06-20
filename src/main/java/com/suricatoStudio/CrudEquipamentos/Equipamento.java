package com.suricatoStudio.CrudEquipamentos;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity @NoArgsConstructor @AllArgsConstructor
public class Equipamento {
	
	@Id @GeneratedValue (strategy= GenerationType.IDENTITY)
	private int id;
	@NotEmpty (message = "Ops! O equipamento precisa de um nome. :/")
	private String nome;
	@NotEmpty (message = "Ops! O equipamento precisa de uma imagem. :/")
	private String imagem;
	@NotNull (message = "Ops! O equipamento precisa de um valor. :/")
	private double valor;
	
}
