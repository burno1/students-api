package com.students.api.domain;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import org.springframework.boot.autoconfigure.EnableAutoConfiguration;

@Entity
@EnableAutoConfiguration
public class Phone {
	
	/* String regex = "^((\\(\\d{2}\\))|\\d{2})[- .]?\\d{5}[- .]?\\d{4}$"; */
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	private String telefone;
	
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		try {
			this.id = id;
		} catch (Exception e) {			
		}
	}
	public String getTelefone() {
		return telefone;
	}
	public void setTelefone(String telefone) {
		this.telefone = telefone;
	}
	
	
	
	

}
