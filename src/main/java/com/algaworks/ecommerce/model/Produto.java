package com.algaworks.ecommerce.model;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.CollectionTable;
import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.ForeignKey;
import javax.persistence.Index;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.Lob;
import javax.persistence.ManyToMany;
import javax.persistence.OneToOne;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

import com.algaworks.ecommerce.listener.GenericListener;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@EntityListeners({GenericListener.class})
@Entity
@Table(name = "produto", 
        uniqueConstraints = { @UniqueConstraint(name = "unq_prod_nome", columnNames = {"nome"})},
        indexes = {@Index(name = "idx_prod_nome", columnList = "nome")})
public class Produto extends EntidadeBaseInteger {

	@Column(name = "data_criacao", updatable = false, nullable = false)
	private LocalDateTime dataCriacao;
	
	@Column(name = "data_ultima_atualizacao", insertable = false)
	private LocalDateTime dataUltimaAtualizacao;
	
	@Column(length = 100, nullable = false) // nome varchar(100) not null
	private String nome;

	@Lob  // descrição longtext..
	private String descricao;
	
	@Lob
	private byte[] foto;

	private BigDecimal preco;
	
	@ManyToMany
	@JoinTable(name = "produto_categoria", 
	           joinColumns = @JoinColumn(name = "produto_id", nullable = false, foreignKey = @ForeignKey(name = "fk_produto_categoria_produto")),
	           inverseJoinColumns = @JoinColumn(name = "categoria_id", nullable = false, foreignKey = @ForeignKey(name = "fk_produto_categoria_categoria")))
	private List<Categoria> categorias;
	
	@OneToOne(mappedBy = "produto")
	private Estoque estoque;
	
	@ElementCollection
	@CollectionTable(name = "produto_tag", joinColumns = @JoinColumn(name = "produto_id"), foreignKey = @ForeignKey(name = "fk_produto_tag_produto"))
	@Column(name = "tag", length = 50, nullable = false)
	private List<String> tags;
	

	@ElementCollection
	@CollectionTable(name = "produto_atributo", joinColumns = @JoinColumn(name = "produto_id"), foreignKey = @ForeignKey(name = "fk_produto_atributo_produto"))
	private List<Atributo> atributos;

	@PrePersist
	public void aoPersistir() {
		System.out.println(">> Callback PrePersist: <<");
		this.dataCriacao = LocalDateTime.now();
	}
	
	@PreUpdate
	public void aoAtualziar() {
		System.out.println(">> Callback PreUpdate: <<");
		this.dataUltimaAtualizacao = LocalDateTime.now();
	}
}
