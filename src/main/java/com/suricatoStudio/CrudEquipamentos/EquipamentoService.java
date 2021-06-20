package com.suricatoStudio.CrudEquipamentos;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class EquipamentoService {
	
	@Autowired
	private EquipamentoRepository repository;
	
	//GET
	public List<Equipamento> getEquipamentos() {
		return repository.findAll();
	}
	
	//GET/1
	public Equipamento getEquipamento(int id) {
		return repository.findById(id).orElse(null);
	}
	
	public double getValorTotal() {
		return repository.getValorTotal();
	}
	
	public long getTotalItens() {
		return repository.count();
	}
	
	//POST
	public Equipamento addEquipamento(Equipamento novo) {
		return repository.save(novo);
	}
	
	//DELETE
	public void deleteEquipamento(int id) {
		repository.deleteById(id);
	}
	
	//PUT
	public Equipamento atualizarEquipamento(int id, Equipamento equip) {
		Equipamento equipamento = repository.findById(id).orElse(null);
		equipamento.setId(id);
		equipamento.setNome(equip.getNome());
		equipamento.setImagem(equip.getImagem());
		equipamento.setValor(equip.getValor());
		return repository.save(equipamento);
	}
}
