package br.com.contability.business;

import java.math.BigDecimal;
import java.util.Calendar;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Index;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.springframework.format.annotation.DateTimeFormat;

import com.fasterxml.jackson.annotation.JsonIgnore;

import br.com.contability.comum.BeanIdentificavel;

@Entity
@Table(name = "saldo", indexes = { @Index(name = "index_saldo", columnList = "id", unique = false)})
public class Saldo extends BeanIdentificavel {
	
	@JsonIgnore
	@ManyToOne(optional = false)
	private Usuario usuario;
	
	@Column(nullable = true)
	private BigDecimal saldoAtual;
	
	@Column(nullable = true)
	@DateTimeFormat(pattern="dd/MM/yyyy")
//	@Temporal(TemporalType.TIMESTAMP)
	private Calendar dataHoraLancamento;

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

	public BigDecimal getSaldoAtual() {
		return saldoAtual;
	}

	public void setSaldoAtual(BigDecimal saldoAtual) {
		this.saldoAtual = saldoAtual;
	}

	public Calendar getDataHoraLancamento() {
		return dataHoraLancamento;
	}

	public void setDataHoraLancamento(Calendar dataHoraLancamento) {
		this.dataHoraLancamento = dataHoraLancamento;
	}
	
}
