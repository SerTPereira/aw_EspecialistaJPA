package com.algaworks.ecommerce.relacionamentos;

import org.junit.Test;

import com.algaworks.ecommerce.EntityManagerTest;
import com.algaworks.ecommerce.model.Pedido;

public class EagerELazyTest extends EntityManagerTest {

	@Test
	public void verificarComportamento() {
		Pedido pedido = entityManager.find(Pedido.class, 1);
		
		pedido.getCliente();
		pedido.getItens();
		
		System.out.println("Vai buscar itens agora.");
		pedido.getItens().isEmpty();
	}
	
}
