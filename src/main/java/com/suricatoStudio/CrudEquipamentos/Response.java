package com.suricatoStudio.CrudEquipamentos;

import java.util.HashMap;
import java.util.Map;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data @NoArgsConstructor
public class Response<T> {
	
	private T data;
	private int status;
	private Map<String, String> errors;
	
	public Map<String, String > getErrors(){
		if(this.errors == null) {
			this.errors = new HashMap<String, String>();
		}
		return errors;
	}
}
