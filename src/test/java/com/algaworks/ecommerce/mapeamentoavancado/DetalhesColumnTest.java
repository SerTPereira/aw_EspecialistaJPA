package com.algaworks.ecommerce.mapeamentoavancado;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.Arrays;

import org.junit.Assert;
import org.junit.Test;

import com.algaworks.ecommerce.EntityManagerTest;
import com.algaworks.ecommerce.model.Cliente;
import com.algaworks.ecommerce.model.EnderecoEntregaPedido;
import com.algaworks.ecommerce.model.ItemPedido;
import com.algaworks.ecommerce.model.Pedido;
import com.algaworks.ecommerce.model.Produto;
import com.algaworks.ecommerce.model.StatusPedido;

public class DetalhesColumnTest extends EntityManagerTest {

	@Test
	public void impedirInsercaoDaColunaAtualizacao() {
		Produto produto = new Produto();
		produto.setNome("Teclado para smartphone");
		produto.setDescricao("O mais confortável");
		produto.setPreco(BigDecimal.ONE);
		produto.setDataCriacao(LocalDateTime.now());
		produto.setDataUltimaAtualizacao(LocalDateTime.now());
		
		entityManager.getTransaction().begin();
		entityManager.persist(produto);
		entityManager.getTransaction().commit();
		
		entityManager.clear();
		
		Produto produtoVerificacao = entityManager.find(Produto.class, produto.getId());
		Assert.assertNotNull(produtoVerificacao.getDataCriacao());
		Assert.assertNull(produtoVerificacao.getDataUltimaAtualizacao());
	}
	
	@Test
	public void impedirAtualizacaoDaColunaCriacao() {
		entityManager.getTransaction().begin();

		Produto produto = entityManager.find(Produto.class, 1);
		produto.setPreco(BigDecimal.TEN);
		produto.setDataCriacao(LocalDateTime.now());
		produto.setDataUltimaAtualizacao(LocalDateTime.now());
				
		entityManager.getTransaction().commit();
		
		entityManager.clear();
		
		Produto produtoVerificacao = entityManager.find(Produto.class, produto.getId());
		Assert.assertNotEquals(produto.getDataCriacao().truncatedTo(ChronoUnit.SECONDS), produtoVerificacao.getDataCriacao().truncatedTo(ChronoUnit.SECONDS));
		Assert.assertEquals(produto.getDataUltimaAtualizacao().truncatedTo(ChronoUnit.SECONDS), produtoVerificacao.getDataUltimaAtualizacao().truncatedTo(ChronoUnit.SECONDS));
	}
	
	@Test
	public void impedirInsercaoDaColunaAtualizacaoPedido() {
		Pedido pedido = new Pedido();
		ItemPedido item = entityManager.find(ItemPedido.class, 1);
		Cliente cliente = entityManager.find(Cliente.class, 1);
		EnderecoEntregaPedido enderecoEntrega = new EnderecoEntregaPedido();
		
		enderecoEntrega.setLogradouro("Av. Kennedy");
		enderecoEntrega.setNumero("1000");
		enderecoEntrega.setComplemento(null);
		enderecoEntrega.setCidade("Indaiatuba");
		enderecoEntrega.setEstado("SP");
		enderecoEntrega.setBairro("Centro");
		enderecoEntrega.setCep("13345-548");
				
		pedido.setCliente(cliente);
		pedido.setDataCriacao(LocalDateTime.now());
		pedido.setDataUltimaAtualizacao(LocalDateTime.now());
		pedido.setEnderecoEntrega(enderecoEntrega);
		pedido.setItens(Arrays.asList(item));
		pedido.setStatus(StatusPedido.AGUARDANDO);
		
		entityManager.getTransaction().begin();
		entityManager.persist(pedido);
		entityManager.getTransaction().commit();
		
		entityManager.clear();
		
		Pedido pedidoVerificacao = entityManager.find(Pedido.class, pedido.getId());
		Assert.assertNotNull(pedidoVerificacao.getDataCriacao());
		Assert.assertNull(pedidoVerificacao.getDataUltimaAtualizacao());
	}
	
	@Test
	public void impedirAtualizacaoDaColunaCriacaoPedido() {
		entityManager.getTransaction().begin();

		Pedido pedido = entityManager.find(Pedido.class, 1);
		pedido.setDataCriacao(LocalDateTime.now());
		pedido.setDataUltimaAtualizacao(LocalDateTime.now());
				
		entityManager.getTransaction().commit();
		
		entityManager.clear();
		
		Pedido pedidoVerificacao = entityManager.find(Pedido.class, pedido.getId());
		Assert.assertNotEquals(pedido.getDataCriacao().truncatedTo(ChronoUnit.SECONDS), pedidoVerificacao.getDataCriacao().truncatedTo(ChronoUnit.SECONDS));
		Assert.assertEquals(pedido.getDataUltimaAtualizacao().truncatedTo(ChronoUnit.SECONDS), pedidoVerificacao.getDataUltimaAtualizacao().truncatedTo(ChronoUnit.SECONDS));
	}
}
;