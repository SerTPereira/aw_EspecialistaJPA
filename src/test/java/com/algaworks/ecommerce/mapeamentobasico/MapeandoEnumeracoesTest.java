package com.algaworks.ecommerce.mapeamentobasico;

import org.junit.Assert;
import org.junit.Test;

import com.algaworks.ecommerce.EntityManagerTest;
import com.algaworks.ecommerce.model.Cliente;
import com.algaworks.ecommerce.model.SexoCliente;

public class MapeandoEnumeracoesTest extends EntityManagerTest{

	
	@Test
	public void testarEnum() {
		Cliente cliente = new Cliente();
		
//		cliente.setId(4); Comentado pq passou a utilizar IDENTITY id
		cliente.setNome("Jos? Moneiro");
		cliente.setSexo(SexoCliente.MASCULINO);
		cliente.setCpf("111");
		
		entityManager.getTransaction().begin();
		entityManager.persist(cliente);
		entityManager.getTransaction().commit();
		
		entityManager.clear();
		
		Cliente clienteVerificacao = entityManager.find(Cliente.class, cliente.getId());
		Assert.assertNotNull(clienteVerificacao);
	}
}
