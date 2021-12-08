package com.algaworks.ecommerce.model;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.PostLoad;
import javax.persistence.PostPersist;
import javax.persistence.PostRemove;
import javax.persistence.PostUpdate;
import javax.persistence.PrePersist;
import javax.persistence.PreRemove;
import javax.persistence.PreUpdate;
import javax.persistence.Table;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Entity
@Table(name = "pedido")
public class Pedido {
	
	@EqualsAndHashCode.Include
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	
	@ManyToOne(optional = false)
	@JoinColumn(name = "cliente_id")
	private Cliente cliente;
	
	@Column(name = "data_criacao")
	private LocalDateTime dataCriacao;
	
	@Column(name = "data_ultima_atualizacao")
	private LocalDateTime dataUltimaAtualizacao;
	
	@Column(name = "data_conclusao")
	private LocalDateTime dataConclusao;
	
	@OneToOne(mappedBy = "pedido")
	private NotaFiscal notaFiscal;
	
	private BigDecimal total;
	
	@Enumerated(EnumType.STRING)
	private StatusPedido status;

	@Embedded
	private EnderecoEntregaPedido enderecoEntrega;

	@OneToMany(mappedBy = "pedido")
	private List<ItemPedido> itens;
	
	@OneToOne(mappedBy = "pedido")
	private PagamentoCartao pagamento;
	

//	-----------------------------------------------------------------------------------------------
//	Callbacks
//	-----------------------------------------------------------------------------------------------	
	
//	@PrePersist
//	@PreUpdate
	public void calcularTotal() {
		if(itens != null) {
			total = itens.stream().map(ItemPedido::getPrecoProduto)
					.reduce(BigDecimal.ZERO, BigDecimal::add);
		}
	}
	
	@PrePersist
	public void aoPersistir() {
		this.dataCriacao = LocalDateTime.now();
		calcularTotal();
	}
	
	@PreUpdate
	public void aoAtualziar() {
		this.dataUltimaAtualizacao = LocalDateTime.now();
		calcularTotal();
	}

	@PostPersist
	public void aposPersistir() {
		System.out.println("Ap�s persistir Pedido.");
	}
	
	@PostUpdate
	public void aposAtualizar() {
		System.out.println("Ap�s atualizar Pedido.");
	}
	
	@PreRemove
	public void aoRemover() {
		System.out.println("Antes de remover Pedido.");
	}
	
	@PostRemove
	public void aposRemover() {
		System.out.println("Ap�s remover Pedido.");
	}
	
	@PostLoad
	public void aoCarregar() {
		System.out.println("Ap�s carregar Pedido.");
	}
	
}
