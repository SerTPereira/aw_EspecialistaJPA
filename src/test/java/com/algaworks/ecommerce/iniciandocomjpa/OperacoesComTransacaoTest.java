package com.algaworks.ecommerce.iniciandocomjpa;

import static org.junit.Assert.assertEquals;

import java.math.BigDecimal;

import org.junit.Assert;
import org.junit.Test;

import com.algaworks.ecommerce.EntityManagerTest;
import com.algaworks.ecommerce.model.Produto;

public class OperacoesComTransacaoTest extends EntityManagerTest {

	@Test
	public void inserirObjetoComMerge() {
		Produto produto = new Produto();
		
		produto.setId(2);
		produto.setNome("Microfone Rode Videmic");
		produto.setDescricao("A melhor qualidade de som");
		produto.setPreco(new BigDecimal(1000));
		
		entityManager.getTransaction().begin();		
		entityManager.merge(produto);		
		entityManager.getTransaction().commit();
		
		entityManager.clear();
		
		Produto produtoVerificacao = entityManager.find(Produto.class, produto.getId());
		Assert.assertNotNull(produtoVerificacao);
	}
	
	@Test
	public void atualizarObjetoGerenciado() {
		
		Produto produto = entityManager.find(Produto.class, 1);
				
		entityManager.getTransaction().begin();
		produto.setNome("Kindle Paperwhite 2� Gera��o");
		entityManager.getTransaction().commit();
		
		entityManager.clear();
		
		Produto produtoVerificacao = entityManager.find(Produto.class, produto.getId());
		assertEquals("Kindle Paperwhite 2� Gera��o", produtoVerificacao.getNome());
		
	}
	
	@Test
	public void atualizarObjeto() {
		
		Produto produto = new Produto();
		
		produto.setId(1);
		produto.setNome("Kindle Paperwhite");
		produto.setDescricao("Conhe�a o novo Kindle.");
		produto.setPreco(new BigDecimal(599));
		
		entityManager.getTransaction().begin();
		entityManager.merge(produto);
		entityManager.getTransaction().commit();
		
		entityManager.clear();
		
		Produto produtoVerificacao = entityManager.find(Produto.class, produto.getId());
		Assert.assertNotNull(produtoVerificacao);
		assertEquals("Kindle Paperwhite", produtoVerificacao.getNome());
		
	}
	
	@Test
	public void removerObjeto() {
//		Produto produto = new Produto();
//		produto.setId(3);

		Produto produto = entityManager.find(Produto.class, 3);
		
		entityManager.getTransaction().begin();
		entityManager.remove(produto);
		entityManager.getTransaction().commit();
		
		Produto produtoVerificacao = entityManager.find(Produto.class, 3);
		Assert.assertNull(produtoVerificacao);
	}
	
	@Test
	public void inserirOPrimeiroObjeto() {
		Produto produto = new Produto();
		
		produto.setId(2);
		produto.setNome("C�mera Canon");
		produto.setDescricao("A melhor defini��o para suas fotos");
		produto.setPreco(new BigDecimal(5000));
		
		entityManager.getTransaction().begin();		
		entityManager.persist(produto);		
		entityManager.getTransaction().commit();
		
		entityManager.clear();
		
		Produto produtoVerificacao = entityManager.find(Produto.class, produto.getId());
		Assert.assertNotNull(produtoVerificacao);
	}
	
	@Test
	public void abrirEFecharATransacao() {
		entityManager.getTransaction().begin();
		
//		entityManager.persist(new Produto());
//		entityManager.merge(new Produto());
//		entityManager.remove(new Produto());
		
		entityManager.getTransaction().commit();
	}
}
