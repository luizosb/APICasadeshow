package com.gft.casadeeventos.model;

import java.math.BigDecimal;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.DecimalMax;
import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.NotEmpty;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.format.annotation.NumberFormat;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

@SuppressWarnings("deprecation")
@Entity
@SequenceGenerator(name="evento_seq", sequenceName="")
public class Evento {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long codigo;
	
	@NotEmpty(message="Insira o nome do evento.")
	@Size(max = 20, message = "O evento não pode ter mais de 100 caracteres.")
	private String nome;
	
	@JsonInclude(Include.NON_NULL)
	@NotNull(message="A capacidade não pode ser zero.")
	@DecimalMin(value ="0", message="A capacidade mínima é de 0 pessoas pelo menos.")
	@DecimalMax(value="60001.00", message="A capacidade máxima deve ser de 60000 pessoas.")
	private Integer capacidade;

	@JsonInclude(Include.NON_NULL)
	@NotNull(message="Insira a data do evento.")
	@DateTimeFormat(pattern = "dd/MM/yyyy")
	@Temporal(TemporalType.DATE)
	private Date data;
	
	private int qtdIngresso;
	
	@JsonInclude(Include.NON_NULL)
	@NotNull(message="Insira o preço do ingresso.")
	@DecimalMin(value ="0.01", message="O preço não pode ser 0 (zero).")
	@DecimalMax(value="4001.00", message="O preço máximo deve ser de 7000 reais.")
	@NumberFormat(pattern = "#,##0.00")
	private BigDecimal preco;
	
	@JsonInclude(Include.NON_NULL)
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(nullable=false)
	@NotNull(message="Insira uma casa.")
	@JsonIgnore 
	private Casadeshow local;
	
	@Enumerated(EnumType.STRING)
	private Genero genero;

	public Long getCodigo() {
		return codigo;
	}

	public void setCodigo(Long codigo) {
		this.codigo = codigo;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public Integer getCapacidade() {
		return capacidade;
	}

	public void setCapacidade(Integer capacidade) {
		this.capacidade = capacidade;
	}

//	public int getQtdIngresso() {
//		return qtdIngresso;
//	}
//
//	public void setqtdIngresso(int qtdIngresso) {
//		this.qtdIngresso = qtdIngresso;
//	}

	public int getqtdIngresso() {
		return qtdIngresso;
	}

	public int setqtdIngresso(int qtdIngresso) {
		return this.qtdIngresso = qtdIngresso;
	}

	public Casadeshow getLocal() {
		return local;
	}

	public void setLocal(Casadeshow local) {
		this.local = local;
	}

	public Genero getGenero() {
		return genero;
	}

	public void setGenero(Genero genero) {
		this.genero = genero;
	}

	public Date getData() {
		return data;
	}

	public void setData(Date data) {
		this.data = data;
	}

	public BigDecimal getPreco() {
		return preco;
	}

	public void setPreco(BigDecimal preco) {
		this.preco = preco;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((codigo == null) ? 0 : codigo.hashCode());
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
		Evento other = (Evento) obj;
		if (codigo == null) {
			if (other.codigo != null)
				return false;
		} else if (!codigo.equals(other.codigo))
			return false;
		return true;
	}

	
	
	
	
	
}
