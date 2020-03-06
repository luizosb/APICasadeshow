package com.gft.casadeeventos.model;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.annotation.JsonProperty;

@Entity
public class Casadeshow {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long ID;
	
	@JsonInclude(Include.NON_NULL)
	@NotEmpty(message="A casa deve possuir um endereço.")
	@Size(max = 60, message="O endereço tem que possuir no máximo 60 caracteres.")
	private String endereco;
	
	@JsonProperty("nomeCasa")
	@JsonInclude(Include.NON_NULL)
	@NotEmpty(message="A casa deve possuir um local.")
	@Size(max = 60, message="O local tem que possuir no máximo 60 caracteres.")
	private String localizacao;
	
	@OneToMany(cascade=CascadeType.ALL, mappedBy="local")
	@JsonIgnore 
	private List<Evento> envents;
	
	public Long getID() {
		return ID;
	}
	public void setID(Long iD) {
		ID = iD;
	}

	public String getEndereco() {
		return endereco;
	}
	public void setEndereco(String endereco) {
		this.endereco = endereco;
	}

	public List<Evento> getEnvents() {
		return envents;
	}
	public void setEnvents(List<Evento> envents) {
		this.envents = envents;
	}
	
	public String getLocalizacao() {
		return localizacao;
	}
	public void setLocalizacao(String localizacao) {
		this.localizacao = localizacao;
	}
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((ID == null) ? 0 : ID.hashCode());
		return result;
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Casadeshow other = (Casadeshow) obj;
		if (ID == null) {
			if (other.ID != null)
				return false;
		} else if (!ID.equals(other.ID))
			return false;
		return true;
	}

	
}
