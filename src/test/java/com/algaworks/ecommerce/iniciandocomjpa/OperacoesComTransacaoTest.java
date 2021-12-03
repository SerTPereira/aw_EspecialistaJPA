package com.algaworks.ecommerce.iniciandocomjpa;

import com.algaworks.ecommerce.EntityManagerTest;

public class OperacoesComTransacaoTest extends EntityManagerTest {

	public void abrirEFecharATransacao() {
		entityManager.getTransaction().begin();
		
//		entityManager.persist(new Produto());
//		entityManager.merge(new Produto());
//		entityManager.remove(new Produto());
		
		entityManager.getTransaction().commit();
	}
}
