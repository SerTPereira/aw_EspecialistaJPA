package com.algaworks.ecommerce.iniciandocomjpa;

import org.junit.Assert;
import org.junit.Test;

import com.algaworks.ecommerce.EntityManagerTest;
import com.algaworks.ecommerce.model.Cliente;

public class PrimeiroCrudTest extends EntityManagerTest {
	
	@Test
	public void createRegistro() {
		Cliente cliente = new Cliente();
		
		cliente.setId(3);
		cliente.setNome("Sergio Pereira");
		
		entityManager.getTransaction().begin();
		entityManager.persist(cliente);
		entityManager.getTransaction().commit();
		
		entityManager.clear();
		
		Cliente clienteVerificacao = entityManager.find(Cliente.class, 3);
		Assert.assertNotNull(clienteVerificacao);
	}
	
	@Test
	public void readRegistro() {
		Cliente cliente = entityManager.find(Cliente.class, 1);
		
		Assert.assertNotNull(cliente);
		Assert.assertEquals("Fernando Medeiros", cliente.getNome());
	}
	
	@Test
	public void updateRegistro() {
		Cliente cliente = entityManager.find(Cliente.class, 1);
		
		cliente.setNome("Fernando Medeiros Silva");
		
		entityManager.getTransaction().begin();
		entityManager.merge(cliente);
		entityManager.getTransaction().commit();
		
		entityManager.clear();		
		
		Cliente clienteVerificacao = entityManager.find(Cliente.class, 1);
		Assert.assertNotNull(clienteVerificacao);
		Assert.assertEquals("Fernando Medeiros Silva", clienteVerificacao.getNome());
		
	}
	
	@Test
	public void deleteRegistro() {
		Cliente cliente = entityManager.find(Cliente.class, 2);
		
		entityManager.getTransaction().begin();
		entityManager.remove(cliente);
		entityManager.getTransaction().commit();
		
		entityManager.clear();		
		
		Cliente clienteVerificacao = entityManager.find(Cliente.class, 2);
		Assert.assertNull(clienteVerificacao);
	}

}
