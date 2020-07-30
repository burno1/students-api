/**
 * 
 */
package com.students.api.domain;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.springframework.boot.autoconfigure.EnableAutoConfiguration;

/**
 * @author Bruno Fernandes
 *
 */
@Entity
@EnableAutoConfiguration
public class Student {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	@NotBlank(message = "Nome eh obrigatorio")
	@NotNull(message="nome nao pode ser nulo")
	@Size(min = 3,message = "Tamanho minimo eh 3")
	private String nome;
	
	@NotNull(message="Sobrenome nao pode ser nulo")
	@Size(min = 3,message = "Tamanho minimo eh 3")
	private String sobrenome;
	
	@NotNull
	@Size(min = 3,message = "Tamanho minimo eh 3")
	@Column(unique = true)
	private String matricula;
	
	@OneToMany(cascade = CascadeType.REMOVE)
	private List<Phone> telefones;

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getMatricula() {
		return matricula;
	}

	public void setMatricula(String matricula) {
		this.matricula = matricula;
	}

	public List<Phone> getTelefones() {
		return telefones;
	}

	public void setTelefones(List<Phone> telefones) {
		this.telefones = telefones;
	}

	public String getSobrenome() {
		return sobrenome;
	}

	public void setSobrenome(String sobrenome) {
		this.sobrenome = sobrenome;
	}

}
