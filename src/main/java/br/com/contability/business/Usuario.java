package br.com.contability.business;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Index;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.NotEmpty;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

import br.com.contability.comum.BeanIdentificavel;

@Entity
@Table(indexes = { @Index(name = "index_usuario", columnList = "id", unique = false),
		@Index(name = "unique_email", columnList = "email", unique = true) })
public class Usuario extends BeanIdentificavel {

	@Column(nullable = false, unique = true)
	@NotEmpty(message = "O email não pode ser vazio")
	@NotNull(message = "O email não pode ser null")
	private String email;

	@Column(length = 200, nullable = false, unique = false)
	@NotEmpty(message = "A senha não pode ser vazio")
	@NotNull(message = "A senha não pode ser null")
	private String senha;

	@Column(length = 200, nullable = true)
	@JsonInclude(Include.NON_NULL)
	private String senhaMaster;

	@Column(nullable = false)
	@NotEmpty(message = "O nome não pode ser vazio")
	@NotNull(message = "O nome não pode ser null")
	private String nome;
	
	@Column(nullable = true)
	private String enderecoImagemPerfil;

	@Column(nullable = true)
	private boolean ativo = true;

	@Column(nullable = true)
	private boolean administrador = true;

	@Column(nullable = true)
	private String fotoPerfil;

	//
	@JsonIgnore
	@OneToMany(mappedBy = "usuario", targetEntity = Categoria.class, fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	private List<Categoria> categorias;
	
	@JsonIgnore
	@OneToMany(mappedBy = "usuario", targetEntity = Conta.class, fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	private List<Conta> contas;
	
	@JsonIgnore
	@OneToMany(mappedBy = "usuario", targetEntity = Lancamento.class, fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	private List<Lancamento> lancamentos;
	
	@JsonIgnore
	@OneToMany(mappedBy = "usuario", targetEntity = Saldo.class, fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	private List<Lancamento> saldos;
	//

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getSenha() {
		return senha;
	}

	public void setSenha(String senha) {
		this.senha = senha;
	}

	public String getSenhaMaster() {
		return senhaMaster;
	}

	public void setSenhaMaster(String senhaMaster) {
		this.senhaMaster = senhaMaster;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public boolean isAtivo() {
		return ativo;
	}

	public void setAtivo(boolean ativo) {
		this.ativo = ativo;
	}

	public boolean isAdministrador() {
		return administrador;
	}

	public void setAdministrador(boolean administrador) {
		this.administrador = administrador;
	}
	
	public String getEnderecoImagemPerfil() {
		return enderecoImagemPerfil;
	}
	
	public void setEnderecoImagemPerfil(String enderecoImagemPerfil) {
		this.enderecoImagemPerfil = enderecoImagemPerfil;
	}

	public String getFotoPerfil() {
		return fotoPerfil;
	}

	public void setFotoPerfil(String fotoPerfil) {
		this.fotoPerfil = fotoPerfil;
	}

	public List<Categoria> getCategorias() {
		return categorias;
	}

	public void setCategorias(List<Categoria> categorias) {
		this.categorias = categorias;
	}
	
	public List<Conta> getContas() {
		return contas;
	}
	
	public void setContas(List<Conta> contas) {
		this.contas = contas;
	}
	
	public List<Lancamento> getLancamentos() {
		return lancamentos;
	}
	
	public void setLancamentos(List<Lancamento> lancamentos) {
		this.lancamentos = lancamentos;
	}
	
	public List<Lancamento> getSaldos() {
		return saldos;
	}
	
	public void setSaldos(List<Lancamento> saldos) {
		this.saldos = saldos;
	}

}
