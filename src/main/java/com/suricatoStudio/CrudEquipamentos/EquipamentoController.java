package com.suricatoStudio.CrudEquipamentos;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin
@RestController
public class EquipamentoController {
	
	@Autowired
	private EquipamentoService service;
	
	@GetMapping("/equipamentos")
	public List<Equipamento> getEquipamentos() {
		return service.getEquipamentos();
	}
	
	@GetMapping("/valorTotal")
	public double totalValor() {
		return service.getValorTotal();
	}
	
	@GetMapping("/itensTotal")
	public long totalItens() {
		return service.getTotalItens();
	}
	
	@GetMapping("/equipamento/{id}")
	public Response<Equipamento> getEquipamento(@PathVariable int id) {
		Response<Equipamento> response = new Response<Equipamento>();
		if(service.getEquipamento(id) == null) {
			response.setStatus(HttpStatus.BAD_REQUEST.value());
			response.getErrors().put("1", "Não existe um equipamento com esse Id");
			return response;
		}
		
		response.setData(service.getEquipamento(id));
		response.setStatus(HttpStatus.OK.value());
		return response;
	}
	
	@PostMapping("/addequipamento")
	public Response<Equipamento> addEquipamento(@Valid @RequestBody Equipamento equipamento, BindingResult result) {
		Response<Equipamento> response = new Response<Equipamento>();
		
		if(result.hasErrors()) {
			response.setStatus(HttpStatus.BAD_REQUEST.value());
			for (ObjectError error : result.getAllErrors()) {
				String key = String.valueOf(response.getErrors().size()+1);
				response.getErrors().put(key, error.getDefaultMessage());
			}
			return response;
		}
		
		response.setData(equipamento);
		service.addEquipamento(equipamento);
		response.setStatus(HttpStatus.OK.value());
		
		return response;
	}
	
	@PutMapping("/atualizarequip/{id}")
	public Response<Equipamento> atualizarEquip(@Valid @RequestBody Equipamento equip, @PathVariable int id, BindingResult result) {
		Response<Equipamento> response = new Response<Equipamento>();
		
		if(service.getEquipamento(id) == null || result.hasErrors()) {
			response.setStatus(HttpStatus.BAD_REQUEST.value());
			for (ObjectError error : result.getAllErrors()) {
				String key = String.valueOf(response.getErrors().size()+1);
				response.getErrors().put(key, error.getDefaultMessage());
			}
			return response;
		}else {
			response.setData(equip);
			service.atualizarEquipamento(id, equip);
			response.setStatus(HttpStatus.OK.value());
			return response;
		}
	}
	
	@DeleteMapping("/deletar/{id}")
	public Response<String> deletarEquipamento(@PathVariable int id) {
		
		Response<String> response = new Response<String>();
		if(service.getEquipamento(id) == null) {
			response.setStatus(HttpStatus.BAD_REQUEST.value());
			response.getErrors().put("1", "Não existe um equipamento com esse Id");
			return response;
		}

		response.setStatus(HttpStatus.OK.value());
		response.setData("Equipamento de id: "+id+" deletado!");
		service.deleteEquipamento(id);
		return response;
	}
	
}
