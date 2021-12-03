package com.algaworks.ecommerce.iniciandocomjpa;

import java.math.BigDecimal;

import org.junit.Assert;
import org.junit.Test;

import com.algaworks.ecommerce.EntityManagerTest;
import com.algaworks.ecommerce.model.Produto;

public class OperacoesComTransacaoTest extends EntityManagerTest {

	@Test
	public void inserirOPrimeiroObjeto() {
		Produto produto = new Produto();
		
		produto.setId(2);
		produto.setNome("Câmera Canon");
		produto.setDescricao("A melhor definição para suas fotos");
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
