package com.suricatoStudio.CrudEquipamentos;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface EquipamentoRepository extends JpaRepository<Equipamento, Integer>{
	
	@Query(value = "select sum(valor) FROM equipamentoscrud.equipamento",  nativeQuery = true)
	double getValorTotal();
	
}
